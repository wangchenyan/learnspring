package me.wcy.spring.printer.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by hzwangchenyan on 2017/12/21.
 */
@Component
public class MQConsumer {

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void process(String message) {
        System.out.println("MQ receive: " + message);
    }
}
