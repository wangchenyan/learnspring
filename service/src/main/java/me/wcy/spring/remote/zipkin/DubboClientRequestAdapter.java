package me.wcy.spring.remote.zipkin;

import com.github.kristofa.brave.ClientRequestAdapter;
import com.github.kristofa.brave.IdConversion;
import com.github.kristofa.brave.KeyValueAnnotation;
import com.github.kristofa.brave.SpanId;
import com.twitter.zipkin.gen.Endpoint;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Created by hzwangchenyan on 2017/11/9.
 */
public class DubboClientRequestAdapter implements ClientRequestAdapter {
    private Map<String, String> headers;
    private String spanName;

    public DubboClientRequestAdapter(Map<String, String> headers, String spanName) {
        this.headers = headers;
        this.spanName = spanName;
    }

    public String getSpanName() {
        return this.spanName;
    }

    public void addSpanIdToRequest(SpanId spanId) {
        if (spanId == null) {
            headers.put(DubboTraceConst.SAMPLED, "0");
        } else {
            headers.put(DubboTraceConst.SAMPLED, "1");
            headers.put(DubboTraceConst.TRACE_ID, IdConversion.convertToString(spanId.traceId));
            headers.put(DubboTraceConst.SPAN_ID, IdConversion.convertToString(spanId.spanId));
            if (spanId.nullableParentId() != null) {
                headers.put(DubboTraceConst.PARENT_SPAN_ID, IdConversion.convertToString(spanId.parentId));
            }
        }
    }

    public Collection<KeyValueAnnotation> requestAnnotations() {
        return Collections.emptyList();
    }

    public Endpoint serverAddress() {
        return null;
    }
}
