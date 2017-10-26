package me.wcy.learnspring.service;

/**
 * Created by hzwangchenyan on 2017/10/26.
 */
public interface KeytoolService {

    boolean genKeystore(String javaPath, String filePath, String alias, String storepass, String keypass, int validity,
                        String name, String organization, String city, String province, String countryCode);
}
