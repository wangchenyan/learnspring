package me.wcy.learnspring;

import me.wcy.learnspring.controller.ASRController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * Created by hzwangchenyan on 2017/10/11.
 */
@SpringBootApplication
@MapperScan("me.wcy.learnspring.dao")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        try {
            ASRController.asr();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
