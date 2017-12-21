package me.wcy.spring.app.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;

/**
 * Created by hzwangchenyan on 2017/12/21.
 */
//@Component
//@RabbitListener(queues = "hello")
public class Receiver {

    @RabbitHandler
    public void process(String message) {
        System.out.println("Receiver: " + message);
    }
}
