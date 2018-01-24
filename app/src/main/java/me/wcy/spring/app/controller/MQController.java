package me.wcy.spring.app.controller;

import me.wcy.spring.app.common.Response;
import me.wcy.spring.app.kafka.KafkaProducer;
import me.wcy.spring.app.mq.MQProducer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hzwangchenyan on 2017/12/21.
 */
@RestController
public class MQController {
    //@Autowired
    private MQProducer mqProducer;
    //@Autowired
    private KafkaProducer kafkaProducer;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public Response sendMessage(@RequestParam("msg") String msg) {
        mqProducer.send(msg);
        kafkaProducer.send(msg);
        return new Response("OK");
    }
}
