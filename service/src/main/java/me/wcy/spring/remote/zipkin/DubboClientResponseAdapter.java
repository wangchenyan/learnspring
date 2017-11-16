package me.wcy.spring.remote.zipkin;

import com.github.kristofa.brave.ClientResponseAdapter;
import com.github.kristofa.brave.KeyValueAnnotation;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by hzwangchenyan on 2017/11/9.
 */
public class DubboClientResponseAdapter implements ClientResponseAdapter {
    private StatusEnum status;

    public DubboClientResponseAdapter(StatusEnum status) {
        this.status = status;
    }

    public Collection<KeyValueAnnotation> responseAnnotations() {
        return Collections.singleton(KeyValueAnnotation.create(DubboTraceConst.STATUS_CODE, status.getDesc()));
    }
}
