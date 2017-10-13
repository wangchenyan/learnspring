package me.wcy.learnspring.service;

import me.wcy.learnspring.common.ServiceRuntimeException;
import me.wcy.learnspring.vo.UserVO;

/**
 * Created by hzwangchenyan on 2017/9/7.
 */
public interface UserService {

    long register(String username, String password, String phoneNumber, String nickname, String signature) throws ServiceRuntimeException;

    UserVO login(String username, String password) throws ServiceRuntimeException;
}
