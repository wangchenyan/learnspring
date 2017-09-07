package me.wcy.learnspring.service.impl;

import me.wcy.learnspring.dao.UserDAO;
import me.wcy.learnspring.po.User;
import me.wcy.learnspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hzwangchenyan on 2017/9/7.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public int insert(User user) {
        return userDAO.insert(user);
    }

    @Override
    public int update(User user) {
        return userDAO.update(user);
    }

    @Override
    public User query(String user_name) {
        return userDAO.query(user_name);
    }
}
