package me.wcy.spring.zipkin;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.github.kristofa.brave.*;

/**
 * Created by hzwangchenyan on 2017/11/9.
 */
@Activate(group = {Constants.PROVIDER, Constants.CONSUMER})
public class BraveDubboFilter implements Filter {
    // 这里不要用注解的方式
    private ClientRequestInterceptor clientRequestInterceptor;
    private ClientResponseInterceptor clientResponseInterceptor;
    private ServerRequestInterceptor serverRequestInterceptor;
    private ServerResponseInterceptor serverResponseInterceptor;

    public void setClientRequestInterceptor(ClientRequestInterceptor clientRequestInterceptor) {
        this.clientRequestInterceptor = clientRequestInterceptor;
    }

    public void setClientResponseInterceptor(ClientResponseInterceptor clientResponseInterceptor) {
        this.clientResponseInterceptor = clientResponseInterceptor;
    }

    public void setServerRequestInterceptor(ServerRequestInterceptor serverRequestInterceptor) {
        this.serverRequestInterceptor = serverRequestInterceptor;
    }

    public void setServerResponseInterceptor(ServerResponseInterceptor serverResponseInterceptor) {
        this.serverResponseInterceptor = serverResponseInterceptor;
    }

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 监控的 dubbo 服务，不纳入跟踪范围
        if ("com.alibaba.dubbo.monitor.MonitorService".equals(invoker.getInterface().getName())) {
            return invoker.invoke(invocation);
        }

        if ("0".equals(invocation.getAttachment(DubboTraceConst.SAMPLED))
                || "false".equals(invocation.getAttachment(DubboTraceConst.SAMPLED))) {
            return invoker.invoke(invocation);
        }

        // 注入
        if (!inject()) {
            return invoker.invoke(invocation);
        }

        StatusEnum status = StatusEnum.OK;
        // 调用的方法名 以此作为 span name
        String methodName = invocation.getMethodName();

        if (RpcContext.getContext().isConsumerSide()) {
            clientRequestInterceptor.handle(new DubboClientRequestAdapter(invocation.getAttachments(), methodName));
            Result result;
            try {
                result = invoker.invoke(invocation);
            } catch (RpcException e) {
                status = StatusEnum.ERROR;
                throw e;
            } finally {
                DubboClientResponseAdapter clientResponseAdapter = new DubboClientResponseAdapter(status);
                clientResponseInterceptor.handle(clientResponseAdapter);
            }
            return result;
        } else if (RpcContext.getContext().isProviderSide()) {
            serverRequestInterceptor.handle(new DubboServerRequestAdapter(RpcContext.getContext().getAttachments(), methodName));
            Result result;
            try {
                result = invoker.invoke(invocation);
            } finally {
                DubboServerResponseAdapter serverResponseAdapter = new DubboServerResponseAdapter(status);
                serverResponseInterceptor.handle(serverResponseAdapter);
            }
            return result;
        }
        return invoker.invoke(invocation);
    }

    private boolean inject() {
        Brave brave = ApplicationContextHolder.getBean(Brave.class);
        if (brave == null) {
            return false;
        }

        setClientRequestInterceptor(brave.clientRequestInterceptor());
        setClientResponseInterceptor(brave.clientResponseInterceptor());
        setServerRequestInterceptor(brave.serverRequestInterceptor());
        setServerResponseInterceptor(brave.serverResponseInterceptor());
        return true;
    }
}
