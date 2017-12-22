package me.wcy.spring.app.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by hzwangchenyan on 2017/12/21.
 */
@Component
public class MQProducer {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Value("${spring.rabbitmq.queue}")
    private String queue;

    public void send(String message) {
        System.out.println("MQ send: " + message);
        rabbitTemplate.convertAndSend(queue, message);
    }
}
