package me.wcy.learnspring;

import me.wcy.learnspring.interceptor.TokenInterceptor;
import me.wcy.learnspring.resolver.UserArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by hzwangchenyan on 2017/10/12.
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

    @Bean
    public TokenInterceptor getTokenInterceptor() {
        return new TokenInterceptor();
    }

    @Bean
    public UserArgumentResolver getUserArgumentResolver() {
        return new UserArgumentResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getTokenInterceptor()).addPathPatterns("/api/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(getUserArgumentResolver());
    }
}
