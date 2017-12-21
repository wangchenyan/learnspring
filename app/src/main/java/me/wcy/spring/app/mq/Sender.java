package me.wcy.spring.app.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hzwangchenyan on 2017/12/21.
 */
@Component
public class Sender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String message) {
        System.out.println("Sender: " + message);
        this.rabbitTemplate.convertAndSend("hello", message);
    }
}
