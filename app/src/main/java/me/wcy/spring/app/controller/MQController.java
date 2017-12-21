package me.wcy.spring.app.controller;

import me.wcy.spring.app.common.Response;
import me.wcy.spring.app.mq.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hzwangchenyan on 2017/12/21.
 */
@RestController
public class MQController {
    @Autowired
    private Sender sender;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public Response sendMessage(@RequestParam("msg") String msg) {
        sender.send(msg);
        return new Response("OK");
    }
}
