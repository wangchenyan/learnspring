package me.wcy.spring.app.service;

import me.wcy.spring.app.vo.UserVO;
import me.wcy.spring.app.common.ServiceRuntimeException;

/**
 * Created by hzwangchenyan on 2017/9/7.
 */
public interface UserService {

    long register(String username, String password, String phoneNumber, String nickname, String signature) throws ServiceRuntimeException;

    UserVO login(String username, String password) throws ServiceRuntimeException;
}
