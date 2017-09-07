package me.wcy.learnspring.service;

import me.wcy.learnspring.po.User;

/**
 * Created by hzwangchenyan on 2017/9/7.
 */
public interface UserService {

    int insert(User user);

    int update(User user);

    User query(String user_name);
}
