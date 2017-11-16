package me.wcy.spring.remote.zipkin;

import com.github.kristofa.brave.KeyValueAnnotation;
import com.github.kristofa.brave.ServerResponseAdapter;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by hzwangchenyan on 2017/11/9.
 */
public class DubboServerResponseAdapter implements ServerResponseAdapter {
    private StatusEnum status;

    public DubboServerResponseAdapter(StatusEnum status) {
        this.status = status;
    }

    public Collection<KeyValueAnnotation> responseAnnotations() {
        return Collections.singleton(KeyValueAnnotation.create(DubboTraceConst.STATUS_CODE, status.getDesc()));
    }
}
