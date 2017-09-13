package me.wcy.learnspring.dao;

import me.wcy.learnspring.po.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;

/**
 * Created by hzwangchenyan on 2017/9/7.
 */
public interface UserDAO {
    String CACHE_PREFIX = "c.user";

    @CacheEvict(value = CACHE_PREFIX, key = "#user.username")
    int insert(User user) throws DataAccessException;

    @CacheEvict(value = CACHE_PREFIX, key = "#user.username")
    int update(User user) throws DataAccessException;

    @Cacheable(value = CACHE_PREFIX, key = "#username")
    User query(String username) throws DataAccessException;
}