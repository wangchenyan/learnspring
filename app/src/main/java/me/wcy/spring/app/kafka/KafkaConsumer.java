package me.wcy.spring.app.kafka;

/**
 * Created by hzwangchenyan on 2017/12/21.
 */
//@Component
public class KafkaConsumer {

    //@KafkaListener(topics = "${spring.kafka.topic}")
    public void processMessage(String message) {
        System.out.println("kafka receive: " + message);
    }
}
