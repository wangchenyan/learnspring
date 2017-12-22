package me.wcy.spring.app.service;

import me.wcy.spring.app.common.ServiceRuntimeException;

/**
 * Created by hzwangchenyan on 2017/10/26.
 */
public interface KeytoolService {

    void genKeystore(String javaPath, String filePath, String alias, String storepass, String keypass, int validity,
                        String name, String organization, String city, String province, String countryCode) throws ServiceRuntimeException;
}
