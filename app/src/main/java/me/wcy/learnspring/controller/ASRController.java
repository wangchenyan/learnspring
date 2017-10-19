package me.wcy.learnspring.controller;

import com.alibaba.fastjson.JSON;
import me.wcy.learnspring.common.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by hzwangchenyan on 2017/10/19.
 */
@RestController
public class ASRController {

    @RequestMapping("/api/asr")
    public Response callback(
            @RequestParam("ret_code") Integer ret_code,
            @RequestParam("ret_msg") String ret_msg,
            @RequestParam("sn") String sn,
            @RequestParam("result") List<String> result,
            HttpServletRequest request
    ) {
        return new Response(JSON.toJSONString(request.getParameterMap()));
    }
}
