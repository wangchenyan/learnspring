package me.wcy.learnspring.controller;

import me.wcy.learnspring.common.HttpClient;
import me.wcy.learnspring.common.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hzwangchenyan on 2017/9/11.
 */
@RestController
public class XiaocaoController {

    @RequestMapping(value = "/getUrl", method = RequestMethod.GET)
    public Response getUrl() {
        String url = "http://get.xunfs.com/app/listapp.php";
        Map<String, String> form = new HashMap<>();
        form.put("a", "get");
        form.put("system", "android");
        form.put("v", "1.4");

        try {
            String result = HttpClient.postFormData(url, null, form);
            return new Response(result);
        } catch (Exception e) {
            return new Response(500, "fail");
        }
    }
}
