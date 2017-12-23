package me.wcy.spring.app;

import me.wcy.spring.app.kafka.KafkaMessage;
import me.wcy.spring.app.kafka.KafkaProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by hzwangchenyan on 2017/12/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaTest {
    private long id;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Test
    public void send() {
        KafkaMessage message = new KafkaMessage();
        message.setId(id++);
        message.setMsg("the message of id " + message.getId());
        message.setTime(System.currentTimeMillis());
        // kafkaProducer.send(JSON.toJSONString(message));
    }
}
