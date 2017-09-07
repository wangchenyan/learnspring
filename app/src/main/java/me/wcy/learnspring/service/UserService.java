package me.wcy.learnspring.service;

import me.wcy.learnspring.common.ServiceRuntimeException;
import me.wcy.learnspring.po.User;

/**
 * Created by hzwangchenyan on 2017/9/7.
 */
public interface UserService {

    int insert(User user) throws ServiceRuntimeException;

    int update(User user) throws ServiceRuntimeException;

    User query(String user_name) throws ServiceRuntimeException;
}
