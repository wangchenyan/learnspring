package me.wcy.spring.xiaocao;

import me.wcy.spring.zipkin.EnableDubboTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by hzwangchenyan on 2017/10/11.
 */
@SpringBootApplication
@EnableDubboTrace
public class XiaocaoApplication {
    public static void main(String[] args) {
        SpringApplication.run(XiaocaoApplication.class, args);
    }
}
