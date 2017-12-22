package me.wcy.spring.printer.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hzwangchenyan on 2017/12/21.
 */
@Configuration
public class MQConfig {
    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @Bean
    public Queue queue() {
        return new Queue(queue);
    }
}
