package me.wcy.spring.app.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by hzwangchenyan on 2017/12/21.
 */
@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String topic;

    public void send(String message) {
        System.out.println("kafka send: " + message);
        kafkaTemplate.send(topic, message);
    }
}
