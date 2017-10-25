package me.wcy.learnspring.controller;

import me.wcy.learnspring.common.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

/**
 * Created by hzwangchenyan on 2017/10/25.
 */
@RestController
public class KeystoreController {
    private static final Logger LOGGER = LogManager.getLogger(KeystoreController.class);

    @RequestMapping("/api/genkey")
    public Response genKeystore() {
        String dir = "/home/keystore/";
        String fileName = "keystore_" + System.currentTimeMillis() + ".jks";
        String path = dir + fileName;
        File file = new File(path);
        boolean result = genKeystore(path, "wangchenyan", "123456", "123456", 100, "wcy", "nt", "hz", "zj", "cn");
        if (result && file.exists()) {
            return new Response("success");
        } else {
            return new Response(500, "error");
        }
    }

    private static boolean genKeystore(String filePath, String alias, String storepass, String keypass, int validity,
                                       String name, String organization, String city, String province, String countryCode) {
        try {
            StringBuilder cmd = new StringBuilder();
            cmd.append("/usr/lib/jvm/java-1.8.0/bin/")
                    .append("keytool -genkey")
                    .append(" -alias ").append(alias)
                    .append(" -keyalg RSA")
                    .append(" -keysize 1024")
                    .append(" -validity ").append(validity)
                    .append(" -keystore ").append(filePath)
                    .append(" -storepass ").append(storepass)
                    .append(" -keypass ").append(keypass)
                    .append(" -dname \"")
                    .append("CN=").append(name)
                    .append(",OU=").append(organization)
                    .append(",O=").append(organization)
                    .append(",L=").append(city)
                    .append(",ST=").append(province)
                    .append(",C=").append(countryCode)
                    .append("\"");
            Runtime.getRuntime().exec(cmd.toString());
            return true;
        } catch (IOException e) {
            LOGGER.error("genKeystore error", e);
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        StringBuffer cmd = new StringBuffer();
        cmd.append("D:\\Java\\jdk-9.0.1\\bin\\");
        cmd.append("keytool -genkey -alias weblogicssl -keyalg RSA -keysize 1024 -validity 365 ");
        cmd.append("-keystore D:/weblogic.jks ");
        cmd.append("-keypass 123456789 -storepass 123456789 ");
        cmd.append("-dname \"CN=localhost,OU=cn,O=c n,L=cn,ST=cn,C=cn\"");
        Process ps = Runtime.getRuntime().exec(cmd.toString());
    }
}
