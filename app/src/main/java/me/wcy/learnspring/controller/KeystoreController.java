package me.wcy.learnspring.controller;

import me.wcy.learnspring.common.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * Created by hzwangchenyan on 2017/10/25.
 */
@RestController
public class KeystoreController {

    @RequestMapping("/api/genkey")
    public Response genKeystore() {
        try {
            String cmd = "keytool -genkeypair -alias \"test1\" -keyalg \"RSA\" -keystore \"test.keystore\"";
            String[] cmdA = {"/bin/sh", "-c", cmd};
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(cmdA);
            LineNumberReader br = new LineNumberReader(new InputStreamReader(process.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                sb.append(line).append("\n");
            }
            return new Response(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return new Response(e.getMessage());
        }
    }
}
