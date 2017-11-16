package me.wcy.spring.remote.zipkin;

/**
 * Created by hzwangchenyan on 2017/11/9.
 */
public interface DubboTraceConst {
    String SAMPLED = "dubbo.trace.sampled";
    String PARENT_SPAN_ID = "dubbo.trace.parentSpanId";
    String SPAN_ID = "dubbo.trace.spanId";
    String TRACE_ID = "dubbo.trace.traceId";
    String STATUS_CODE = "dubbo.trace.staus_code";
}
