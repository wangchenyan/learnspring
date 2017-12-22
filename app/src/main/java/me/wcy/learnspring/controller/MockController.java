package me.wcy.learnspring.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wcy on 2017/11/25.
 */
@RestController
public class MockController {

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public String login(@RequestBody String body) {
        System.out.println("body: " + body);
        return "{\n" +
                "    \"code\": 200,\n" +
                "    \"msg\": \"success\",\n" +
                "    \"data\": {\n" +
                "        \"token\": \"abcd\"\n" +
                "    }\n" +
                "}";
    }
}
