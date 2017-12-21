package me.wcy.spring.printer;

import me.wcy.spring.zipkin.EnableDubboTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by hzwangchenyan on 2017/11/9.
 */
@SpringBootApplication
@EnableDubboTrace
public class PrinterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrinterApplication.class, args);
    }
}
