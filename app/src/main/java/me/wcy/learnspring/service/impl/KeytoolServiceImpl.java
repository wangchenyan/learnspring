package me.wcy.learnspring.service.impl;

import me.wcy.learnspring.service.KeytoolService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by hzwangchenyan on 2017/10/26.
 */
@Service
public class KeytoolServiceImpl implements KeytoolService {
    private static final Logger LOGGER = LogManager.getLogger(KeytoolServiceImpl.class);

    @Override
    public boolean genKeystore(String javaPath, String filePath, String alias, String storepass, String keypass, int validity,
                               String name, String organization, String city, String province, String countryCode) {
        try {
            StringBuilder cmd = new StringBuilder();
            cmd.append(javaPath)
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
