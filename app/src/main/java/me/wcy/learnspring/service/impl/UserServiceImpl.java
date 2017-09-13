package me.wcy.learnspring.service.impl;

import me.wcy.learnspring.common.ServiceRuntimeException;
import me.wcy.learnspring.dao.UserDAO;
import me.wcy.learnspring.po.User;
import me.wcy.learnspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * Created by hzwangchenyan on 2017/9/7.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public int insert(User user) throws ServiceRuntimeException {
        try {
            return userDAO.insert(user);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new ServiceRuntimeException("insert error", e);
        }
    }

    @Override
    public int update(User user) throws ServiceRuntimeException {
        try {
            return userDAO.update(user);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new ServiceRuntimeException("update error", e);
        }
    }

    @Override
    public User query(String username) throws ServiceRuntimeException {
        try {
            return userDAO.query(username);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new ServiceRuntimeException("query error", e);
        }
    }
}
