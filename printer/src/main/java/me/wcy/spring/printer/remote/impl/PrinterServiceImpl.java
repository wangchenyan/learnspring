package me.wcy.spring.printer.remote.impl;

import com.alibaba.dubbo.config.annotation.Service;
import me.wcy.spring.remote.printer.PrinterService;

/**
 * Created by hzwangchenyan on 2017/11/9.
 */
@Service(version = "1.0.0")
public class PrinterServiceImpl implements PrinterService {

    @Override
    public String print(String content) {
        return "Print:\n" + content;
    }
}
