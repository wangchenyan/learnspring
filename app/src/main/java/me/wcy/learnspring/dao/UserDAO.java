package me.wcy.learnspring.dao;

import me.wcy.learnspring.po.User;
import org.springframework.dao.DataAccessException;

/**
 * Created by hzwangchenyan on 2017/9/7.
 */
public interface UserDAO {
    int insert(User user) throws DataAccessException;

    int update(User user) throws DataAccessException;

    User query(String user_name) throws DataAccessException;
}