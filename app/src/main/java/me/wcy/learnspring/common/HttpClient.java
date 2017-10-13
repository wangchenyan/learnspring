package me.wcy.learnspring.common;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by hzwangchenyan on 2017/10/13.
 */
public class HttpClient {
    private static final String UA = "HttpClient";

    public static String postFormData(String url, Map<String, String> params) throws Exception {
        URL httpUrl = new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection) httpUrl.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.addRequestProperty("User-Agent", UA);
        urlConnection.addRequestProperty("charset", "UTF-8");
        urlConnection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        urlConnection.setReadTimeout(30 * 1000);
        urlConnection.setConnectTimeout(30 * 1000);
        urlConnection.setUseCaches(false);
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);

        StringBuilder data = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (data.length() > 0) {
                data.append("&");
            }
            data.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue()));
        }
        DataOutputStream out = new DataOutputStream(urlConnection.getOutputStream());
        out.write(data.toString().getBytes("UTF-8"));
        out.close();

        int resCode = urlConnection.getResponseCode();

        if (resCode != HttpURLConnection.HTTP_OK) {
            throw new Exception("request failed, code: " + resCode);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len;
        byte buffer[] = new byte[1024];
        while ((len = urlConnection.getInputStream().read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        String result = new String(bos.toByteArray(), "UTF-8");
        bos.close();
        return result;
    }
}
