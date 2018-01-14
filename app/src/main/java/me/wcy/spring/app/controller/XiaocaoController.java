package me.wcy.spring.app.controller;

import me.wcy.spring.app.common.HttpClient;
import me.wcy.spring.app.common.Response;
import me.wcy.spring.app.remote.consumer.PrinterConsumerService;
import me.wcy.spring.app.remote.consumer.XiaocaoConsumerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hzwangchenyan on 2017/9/11.
 */
@RestController
public class XiaocaoController {
    private static final Logger LOGGER = LogManager.getLogger(XiaocaoController.class);

    @Autowired
    private XiaocaoConsumerService xiaocaoConsumerService;
    @Autowired
    private PrinterConsumerService printerConsumerService;

    @RequestMapping(value = "/xiaocao", method = RequestMethod.GET)
    public Response xiaocao() {
//        if (xiaocaoConsumerService == null) {
//            return new Response("null");
//        }
//        return new Response(xiaocaoConsumerService.getXiaocao());

        String url = "http://get.xunfs.com/app/listapp.php";
        Map<String, String> form = new HashMap<>();
        form.put("a", "get");
        form.put("system", "android");
        form.put("v", "1.4");

        String result;
        try {
            result = HttpClient.postFormData(url, null, form);
        } catch (Exception e) {
            result = "fail";
        }
        return new Response(result);
    }

    @RequestMapping(value = "print", method = RequestMethod.GET)
    public String print(@RequestParam("content") String content) {
        return printerConsumerService.print(content);
    }
}
