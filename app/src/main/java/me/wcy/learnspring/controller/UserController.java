package me.wcy.learnspring.controller;

import me.wcy.learnspring.common.Response;
import me.wcy.learnspring.common.ResponseCode;
import me.wcy.learnspring.common.ServiceRuntimeException;
import me.wcy.learnspring.po.User;
import me.wcy.learnspring.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wcy on 2017/9/3.
 */
@Controller
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Response register(
            @RequestParam("u") String userName,
            @RequestParam("p") String password,
            @RequestParam(value = "phone", required = false) String phoneNumber,
            @RequestParam(value = "nick", required = false) String nickname,
            @RequestParam(value = "sign", required = false) String signature
    ) {
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            return new Response(ResponseCode.GLOBAL_PARAM_ERROR, "param error");
        }

        User user = new User();
        user.setUser_name(userName);
        user.setPassword(password);
        user.setPhone_number(phoneNumber);
        user.setNickname(nickname);
        user.setSignature(signature);
        try {
            userService.insert(user);
        } catch (ServiceRuntimeException e) {
            e.printStackTrace();
            return new Response(ResponseCode.GLOBAL_SERVER_ERROR, "error");
        }

        return new Response(ResponseCode.GLOBAL_SUCCESS, "success");
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response getTitle(@RequestParam("u") String userName) {
        try {
            User user = userService.query(userName);
            if (user != null) {
                return new Response(user);
            } else {
                return new Response(ResponseCode.GLOBAL_DATA_NOT_EXIST, "user not exist");
            }
        } catch (ServiceRuntimeException e) {
            e.printStackTrace();
            return new Response(ResponseCode.GLOBAL_SERVER_ERROR, "error");
        }
    }
}
