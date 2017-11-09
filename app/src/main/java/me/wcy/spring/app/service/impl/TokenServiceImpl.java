package me.wcy.spring.app.service.impl;

import me.wcy.spring.app.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by hzwangchenyan on 2017/9/15.
 */
@Service
public class TokenServiceImpl implements TokenService {
    private static final String KEY_TOKEN_PREFIX = "KEY_TOKEN_";
    private static final long TOKEN_EXPIRES_DAY = 15;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String createToken(Long userId) {
        String token = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        redisTemplate.boundValueOps(getKey(userId)).set(token, TOKEN_EXPIRES_DAY, TimeUnit.DAYS);
        return token;
    }

    @Override
    public boolean checkToken(Long userId, String token) {
        String cacheToken = redisTemplate.boundValueOps(getKey(userId)).get();
        if (cacheToken != null && cacheToken.equals(token)) {
            redisTemplate.boundValueOps(getKey(userId)).expire(TOKEN_EXPIRES_DAY, TimeUnit.DAYS);
            return true;
        }
        return false;
    }

    @Override
    public void deleteToken(Long userId) {
        redisTemplate.delete(getKey(userId));
    }

    private String getKey(Long userId) {
        return KEY_TOKEN_PREFIX + userId;
    }
}
