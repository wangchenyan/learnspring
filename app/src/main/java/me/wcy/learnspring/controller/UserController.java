package me.wcy.learnspring.controller;

import me.wcy.learnspring.common.Response;
import me.wcy.learnspring.common.ResponseCode;
import me.wcy.learnspring.common.ServiceRuntimeException;
import me.wcy.learnspring.interceptor.annotation.TokenCheck;
import me.wcy.learnspring.po.User;
import me.wcy.learnspring.resolver.annotation.ResolveUser;
import me.wcy.learnspring.service.UserService;
import me.wcy.learnspring.vo.UserVO;
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
            @RequestParam("u") String username,
            @RequestParam("p") String password,
            @RequestParam(value = "phone", required = false) String phoneNumber,
            @RequestParam(value = "nick", required = false) String nickname,
            @RequestParam(value = "sign", required = false) String signature
    ) {
        try {
            userService.register(username, password, phoneNumber, nickname, signature);
            return new Response(ResponseCode.GLOBAL_SUCCESS, "success");
        } catch (ServiceRuntimeException e) {
            e.printStackTrace();
            return new Response(e.getCode(), e.getMessage());
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public Response login(
            @RequestParam("u") String username,
            @RequestParam("p") String password) {
        try {
            UserVO userVO = userService.login(username, password);
            return new Response(userVO);
        } catch (ServiceRuntimeException e) {
            e.printStackTrace();
            return new Response(e.getCode(), e.getMessage());
        }
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    @TokenCheck
    public Response getUserInfo(
            @ResolveUser User user
    ) {
        if (user == null) {
            return new Response(ResponseCode.GLOBAL_SERVER_ERROR, "error");
        } else {
            UserVO userVO = UserVO.newUserVO(user);
            return new Response(userVO);
        }
    }
}
