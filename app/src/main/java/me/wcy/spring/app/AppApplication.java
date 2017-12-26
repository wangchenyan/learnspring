package me.wcy.spring.app;

import me.wcy.spring.app.chat.PushServer;
import me.wcy.spring.zipkin.EnableDubboTrace;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by hzwangchenyan on 2017/10/11.
 */
@SpringBootApplication
@MapperScan("me.wcy.spring.app.dao")
@EnableDubboTrace
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);

        //ASRController.asr();
        PushServer.start();
    }
}
