package me.wcy.spring.app.common;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by hzwangchenyan on 2017/10/13.
 */
public class HttpClient {
    private static final String UA = "HttpClient";

    interface Method {
        String GET = "GET";
        String POST = "POST";
    }

    interface ContentType {
        String TEXT_PLAIN = "text/plain; charset=UTF-8 ";
        String FORM = "application/x-www-form-urlencoded";
        String JSON = "application/json";
    }

    public static String get(String url, Map<String, String> params) throws IOException {
        if (params != null && !params.isEmpty()) {
            url = buildUrl(url, params);
        }
        return get(url);
    }

    public static String postText(String url, Map<String, String> params, String data) throws IOException {
        return postData(url, params, data, ContentType.TEXT_PLAIN);
    }

    public static String postFormData(String url, Map<String, String> params, Map<String, String> form) throws IOException {
        StringBuilder data = new StringBuilder();
        for (Map.Entry<String, String> entry : form.entrySet()) {
            if (data.length() > 0) {
                data.append("&");
            }
            data.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue()));
        }
        return postData(url, params, data.toString(), ContentType.FORM);
    }

    private static String postData(String url, Map<String, String> params, String data, String contentType) throws IOException {
        if (params != null && !params.isEmpty()) {
            url = buildUrl(url, params);
        }
        return post(url, data, contentType);
    }

    private static String get(String url) throws IOException {
        HttpURLConnection urlConnection = buildUrlConnection(url);
        urlConnection.setRequestMethod(Method.GET);
        return request(urlConnection);
    }

    private static String post(String url, String data, String contentType) throws IOException {
        HttpURLConnection urlConnection = buildUrlConnection(url);
        urlConnection.setRequestMethod(Method.POST);
        if (!StringUtils.isEmpty(contentType)) {
            urlConnection.addRequestProperty("Content-Type", contentType);
        }

        if (!StringUtils.isEmpty(data)) {
            urlConnection.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(urlConnection.getOutputStream());
            out.write(data.getBytes("UTF-8"));
            out.flush();
            out.close();
        }

        return request(urlConnection);
    }

    private static String request(HttpURLConnection urlConnection) throws IOException {
        int resCode = urlConnection.getResponseCode();

        if (resCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("request failed, code: " + resCode);
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

    private static HttpURLConnection buildUrlConnection(String url) throws IOException {
        URL httpUrl = new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection) httpUrl.openConnection();
        urlConnection.addRequestProperty("User-Agent", UA);
        urlConnection.addRequestProperty("charset", "UTF-8");
        urlConnection.setReadTimeout(30 * 1000);
        urlConnection.setConnectTimeout(30 * 1000);
        urlConnection.setUseCaches(false);
        return urlConnection;
    }

    private static String buildUrl(String url, Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            url = url.concat(url.contains("?") ? "&" : "?")
                    .concat(entry.getKey())
                    .concat("=")
                    .concat(URLEncoder.encode(entry.getValue()));
        }
        return url;
    }
}
