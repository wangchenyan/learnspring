package me.wcy.learnspring.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qcloud.cos.request.UploadFileRequest;
import me.wcy.learnspring.common.COS;
import me.wcy.learnspring.common.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

/**
 * Created by hzwangchenyan on 2017/10/25.
 */
@RestController
@ConfigurationProperties(prefix = "keystore")
public class KeystoreController {
    private static final Logger LOGGER = LogManager.getLogger(KeystoreController.class);

    private String javaPath;
    private String tempPath;

    public String getJavaPath() {
        return javaPath;
    }

    public String getTempPath() {
        return tempPath;
    }

    @RequestMapping(value = "/api/genkey", method = RequestMethod.POST)
    public Response genKeystore(
            @RequestParam("alias") String alias,
            @RequestParam("storepass") String storepass,
            @RequestParam("keypass") String keypass,
            @RequestParam("validity") Integer validity,
            @RequestParam("name") String name,
            @RequestParam("organization") String organization,
            @RequestParam("city") String city,
            @RequestParam("province") String province,
            @RequestParam("countryCode") String countryCode
    ) {
        if (alias.contains(" ")
                || storepass.contains(" ")
                || keypass.contains(" ")
                || storepass.length() < 6
                || keypass.length() < 6
                || validity <= 0) {
            return new Response(400, "param format error");
        }

        String dir = javaPath;
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        } else if (dirFile.isFile()) {
            dirFile.delete();
            dirFile.mkdirs();
        }

        String fileName = "keystore_" + System.currentTimeMillis() + ".jks";
        String path = dir + fileName;
        File file = new File(path);

        boolean result = genKeystore(path, alias, storepass, keypass, validity, name, organization, city, province, countryCode);
        if (result && file.exists()) {
            UploadFileRequest uploadFileRequest = new UploadFileRequest(COS.BUCKET, "/" + file.getName(), file.getAbsolutePath());
            String uploadFileRet = COS.getCOSClient().uploadFile(uploadFileRequest);
            JSONObject resultObject = JSON.parseObject(uploadFileRet);
            int code = resultObject.getIntValue("code");
            String message = resultObject.getString("message");
            JSONObject data = resultObject.getJSONObject("data");
            String downloadUrl = null;
            if (data != null) {
                downloadUrl = data.getString("access_url");
            }
            if (code == 0) {
                return new Response(downloadUrl);
            } else {
                return new Response(500, "upload file to COS failed, code: " + code + ", message: " + message);
            }
        }

        return new Response(500, "generate keystore failed");
    }

    private boolean genKeystore(String filePath, String alias, String storepass, String keypass, int validity,
                                String name, String organization, String city, String province, String countryCode) {
        try {
            StringBuilder cmd = new StringBuilder();
            cmd.append(tempPath)
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
            LOGGER.info("genKeystore, cmd: " + cmd.toString());
            String[] cmds = new String[]{"/bin/sh", "-c", cmd.toString()};
            Process process = Runtime.getRuntime().exec(cmds);
            process.waitFor();
            return true;
        } catch (IOException | InterruptedException e) {
            LOGGER.error("genKeystore error", e);
        }

        return false;
    }
}
