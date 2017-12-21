package me.wcy.spring.zipkin;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by hzwangchenyan on 2017/11/9.
 */
public class ApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        setCtx(ctx);
    }

    private static void setCtx(ApplicationContext ctx) {
        applicationContext = ctx;
    }

    public static <T> T getBean(Class<T> requiredType) {
        if (applicationContext != null) {
            return applicationContext.getBean(requiredType);
        }
        return null;
    }
}
