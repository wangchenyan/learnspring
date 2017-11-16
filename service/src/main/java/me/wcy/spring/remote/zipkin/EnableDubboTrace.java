package me.wcy.spring.remote.zipkin;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by hzwangchenyan on 2017/11/9.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ZipkinConfiguration.class, DubboTraceConfiguration.class})
public @interface EnableDubboTrace {
}