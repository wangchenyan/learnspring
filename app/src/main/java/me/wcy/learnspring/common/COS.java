package me.wcy.learnspring.common;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.sign.Credentials;

/**
 * Created by hzwangchenyan on 2017/10/25.
 */
public class COS {
    public static final String BUCKET = "keystore";
    private static COSClient cosClient;

    public static COSClient getCOSClient() {
        if (cosClient == null) {
            cosClient = initCOSClient();
        }
        return cosClient;
    }

    private static COSClient initCOSClient() {
        long appId = 1255343135;
        String secretId = "AKIDUCmD8vnUcdVfhXqzRhlhRyLXWJDyBs5J";
        String secretKey = "Rimr0xkMmzztnV0IQfjaHm5HMPFtV5TY";
        // 初始化秘钥信息
        Credentials cred = new Credentials(appId, secretId, secretKey);

        // 初始化客户端配置
        ClientConfig clientConfig = new ClientConfig();
        // 设置bucket所在的区域，比如华南园区：gz； 华北园区：tj；华东园区：sh ；
        clientConfig.setRegion("sh");

        // 初始化cosClient
        return new COSClient(clientConfig, cred);
    }
}
