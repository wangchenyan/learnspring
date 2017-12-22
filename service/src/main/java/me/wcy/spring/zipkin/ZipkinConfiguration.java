package me.wcy.spring.zipkin;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
import com.github.kristofa.brave.Sampler;
import com.github.kristofa.brave.SpanCollector;
import com.github.kristofa.brave.http.HttpSpanCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Created by hzwangchenyan on 2017/11/9.
 */
@Configuration
public class ZipkinConfiguration {
    @Autowired
    private Environment env;

    @Bean
    public SpanCollector spanCollector() {
        return HttpSpanCollector.create(env.getProperty("zipkin.baseUrl"), new EmptySpanCollectorMetricsHandler());
    }

    @Bean
    public Brave brave(SpanCollector spanCollector) {
        return new Brave.Builder(env.getProperty("zipkin.serviceName"))
                .spanCollector(spanCollector)
                .traceSampler(Sampler.create(1))
                .build();
    }
}
