package me.wcy.spring.xiaocao.remote.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import me.wcy.spring.remote.printer.PrinterService;
import org.springframework.stereotype.Component;

/**
 * Created by hzwangchenyan on 2017/11/9.
 */
@Component
public class PrinterConsumerService implements PrinterService {
    @Reference(version = "1.0.0")
    private PrinterService printerService;

    @Override
    public String print(String content) {
        return printerService.print(content);
    }
}
