package me.wcy.spring.xiaocao.remote.impl;

import com.alibaba.dubbo.config.annotation.Service;
import me.wcy.spring.remote.xiaocao.XiaocaoService;
import me.wcy.spring.xiaocao.common.HttpClient;
import me.wcy.spring.xiaocao.remote.consumer.PrinterConsumerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hzwangchenyan on 2017/11/6.
 */
@Service(version = "1.0.0")
public class XiaocaoServiceImpl implements XiaocaoService {
    @Autowired
    private PrinterConsumerService printerConsumerService;

    @Override
    public String getXiaocao() {
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

        return printerConsumerService.print(result);
    }
}
