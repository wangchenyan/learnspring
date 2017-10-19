package me.wcy.learnspring.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.wcy.learnspring.Application;
import me.wcy.learnspring.common.HttpClient;
import me.wcy.learnspring.common.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by hzwangchenyan on 2017/10/19.
 */
@RestController
public class ASRController {

    private static final Logger LOGGER = LogManager.getLogger(ASRController.class);

    @RequestMapping("/api/asr")
    public Response callback(
            @RequestBody String result
    ) {
        JSONObject object = JSON.parseObject(result);
        int code = object.getIntValue("ret_code");
        if (code == 1) {
            String sn = object.getString("sn");
            String url = Application.snMap.get(sn);
            double duration = object.getDoubleValue("audio_duration");
            String text = object.getJSONArray("result").get(0).toString();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("url", url);
            jsonObject.put("duration", duration);
            jsonObject.put("text", text);

            LOGGER.info(jsonObject.toJSONString());
        }
        return new Response("");
    }

    public static void main(String[] args) throws IOException {
        String url = "http://api.vop.netease.com/callback/asr_api?cuid=wangchenyan&token=0a3318fed788ccbe4c24b15bef757fc6&callback=http://114.67.154.97:8080/api/asr";
        String data = "https://nos.netease.com/ysf/857317f55e23b641855119943ed42889.wav";
        String result = HttpClient.postText(url, null, data);
        System.out.println(result);
    }
}
