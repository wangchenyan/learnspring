package me.wcy.learnspring.controller;

import me.wcy.learnspring.common.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hzwangchenyan on 2017/9/11.
 */
@Controller
public class XiaocaoController {

    @RequestMapping(value = "/getUrl", method = RequestMethod.GET)
    @ResponseBody
    public Response getUrl() throws IOException {
        String url = "http://get.xunfs.com/app/listapp.php";
        URL httpUrl = new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection) httpUrl.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("User-Agent", "Common");
        urlConnection.setReadTimeout(30 * 1000);
        urlConnection.setConnectTimeout(30 * 1000);
        urlConnection.setUseCaches(false);
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);

        String params = "a=get&system=android&v=1.4";
        DataOutputStream out = new DataOutputStream(urlConnection.getOutputStream());
        out.write(params.getBytes());
        out.close();

        int resCode = urlConnection.getResponseCode();
        if (resCode == HttpURLConnection.HTTP_OK) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len;
            byte buffer[] = new byte[1024];
            while ((len = urlConnection.getInputStream().read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            String result = new String(bos.toByteArray(), "UTF-8");
            bos.close();
            return new Response(result);
        }

        return new Response(500, "fail");
    }
}
