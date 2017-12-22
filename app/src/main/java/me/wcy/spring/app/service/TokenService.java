package me.wcy.spring.app.service;

/**
 * Created by hzwangchenyan on 2017/9/15.
 */
public interface TokenService {

    String createToken(Long userId);

    boolean checkToken(Long userId, String token);

    void deleteToken(Long userId);
}
