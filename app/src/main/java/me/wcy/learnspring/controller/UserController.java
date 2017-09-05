package me.wcy.learnspring.controller;

import me.wcy.learnspring.model.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wcy on 2017/9/3.
 */
@Controller
@RequestMapping("/api")
public class UserController {

    @RequestMapping(value = "/title", method = RequestMethod.GET)
    @ResponseBody
    public Response getTitle() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("姓名", "王晨彦");
        return new Response(map);
    }
}
