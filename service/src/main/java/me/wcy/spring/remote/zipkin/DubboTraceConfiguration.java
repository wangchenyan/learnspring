package me.wcy.spring.remote.zipkin;

import com.github.kristofa.brave.Brave;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hzwangchenyan on 2017/11/9.
 */
@Configuration
@ConditionalOnClass(Brave.class)
@ConditionalOnProperty("zipkin.enable")
public class DubboTraceConfiguration {

    @Bean
    public ApplicationContextAware holder() {
        System.out.println(getClass().getSimpleName() + " holder");
        return new ApplicationContextHolder();
    }
}
