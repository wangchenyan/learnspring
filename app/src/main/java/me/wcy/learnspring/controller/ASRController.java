package me.wcy.learnspring.controller;

import me.wcy.learnspring.common.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hzwangchenyan on 2017/10/19.
 */
@RestController
public class ASRController {

    @RequestMapping("/api/asr")
    public Response callback(
            @RequestBody String result
    ) {
        System.out.println(result);
        return new Response(result);
    }
}
