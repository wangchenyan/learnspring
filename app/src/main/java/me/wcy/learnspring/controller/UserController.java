package me.wcy.learnspring.controller;

import me.wcy.learnspring.common.Response;
import me.wcy.learnspring.common.ResponseCode;
import me.wcy.learnspring.common.ServiceRuntimeException;
import me.wcy.learnspring.po.User;
import me.wcy.learnspring.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;

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
            @RequestParam("u") String username,
            @RequestParam("p") String password,
            @RequestParam(value = "phone", required = false) String phoneNumber,
            @RequestParam(value = "nick", required = false) String nickname,
            @RequestParam(value = "sign", required = false) String signature
    ) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return new Response(ResponseCode.GLOBAL_PARAM_ERROR, "param error");
        }

        try {
            User origin = userService.query(username);
            if (origin != null) {
                return new Response(ResponseCode.GLOBAL_ILLEGAL_REQUEST, "user exist");
            }

            User user = new User();
            user.setUsername(username);
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes()).toUpperCase();
            user.setPassword(md5Password);
            user.setPhone_number(phoneNumber);
            user.setNickname(nickname);
            user.setSignature(signature);
            user.setDb_create_time(new Timestamp(System.currentTimeMillis()));
            user.setDb_update_time(new Timestamp(System.currentTimeMillis()));
            userService.insert(user);
        } catch (ServiceRuntimeException e) {
            e.printStackTrace();
            return new Response(ResponseCode.GLOBAL_SERVER_ERROR, "error");
        }

        return new Response(ResponseCode.GLOBAL_SUCCESS, "success");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public Response login(
            @RequestParam("u") String username,
            @RequestParam("p") String password) {
        try {
            User user = userService.query(username);
            if (user != null) {
                if (StringUtils.equalsIgnoreCase(user.getPassword(), password)) {
                    return new Response(user);
                } else {
                    return new Response(ResponseCode.USER_AUTH_FAILED, "username or password error");
                }
            } else {
                return new Response(ResponseCode.GLOBAL_DATA_NOT_EXIST, "user not exist");
            }
        } catch (ServiceRuntimeException e) {
            e.printStackTrace();
            return new Response(ResponseCode.GLOBAL_SERVER_ERROR, "error");
        }
    }
}
