package me.wcy.spring.remote.zipkin;

import com.github.kristofa.brave.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Created by hzwangchenyan on 2017/11/9.
 */
public class DubboServerRequestAdapter implements ServerRequestAdapter {
    private Map<String, String> headers;
    private String spanName;

    public DubboServerRequestAdapter(Map<String, String> headers, String spanName) {
        this.headers = headers;
        this.spanName = spanName;
    }

    public TraceData getTraceData() {
        final String sampled = headers.get(DubboTraceConst.SAMPLED);
        if (sampled != null) {
            if (sampled.equals("0") || sampled.toLowerCase().equals("false")) {
                return TraceData.builder().sample(false).build();
            } else {
                String parentSpanId = headers.get(DubboTraceConst.PARENT_SPAN_ID);
                String traceId = headers.get(DubboTraceConst.TRACE_ID);
                String spanId = headers.get(DubboTraceConst.SPAN_ID);
                if (traceId != null && spanId != null) {
                    SpanId span = getSpanId(traceId, spanId, parentSpanId);
                    return TraceData.builder().sample(true).spanId(span).build();
                }
            }
        }
        return TraceData.builder().build();
    }

    public String getSpanName() {
        return this.spanName;
    }

    public Collection<KeyValueAnnotation> requestAnnotations() {
        return Collections.emptyList();
    }

    private static SpanId getSpanId(String traceId, String spanId, String parentSpanId) {
        return SpanId.builder()
                .traceId(IdConversion.convertToLong(traceId))
                .spanId(IdConversion.convertToLong(spanId))
                .parentId(parentSpanId == null ? null : IdConversion.convertToLong(parentSpanId))
                .build();
    }
}
