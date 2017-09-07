package me.wcy.learnspring.controller;

import me.wcy.learnspring.po.User;
import me.wcy.learnspring.service.UserService;
import me.wcy.learnspring.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wcy on 2017/9/3.
 */
@Controller
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/title", method = RequestMethod.GET)
    @ResponseBody
    public Response getTitle() {
        User user = userService.query("wangchenyan");
        Object result = user == null ? "failed" : user;
        return new Response(result);
    }
}
