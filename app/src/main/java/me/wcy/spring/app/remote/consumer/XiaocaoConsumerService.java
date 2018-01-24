package me.wcy.spring.app.remote.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import me.wcy.spring.remote.xiaocao.XiaocaoService;

/**
 * Created by hzwangchenyan on 2017/11/8.
 */
//@Component
public class XiaocaoConsumerService implements XiaocaoService {
    @Reference(version = "1.0.0")
    private XiaocaoService xiaocaoService;

    @Override
    public String getXiaocao() {
        return xiaocaoService.getXiaocao();
    }
}
