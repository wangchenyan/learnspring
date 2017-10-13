package me.wcy.learnspring.dao;

import me.wcy.learnspring.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;

/**
 * Created by hzwangchenyan on 2017/9/7.
 */
public interface UserDAO {
    String CACHE_PREFIX = "c.user";

    @CacheEvict(value = CACHE_PREFIX, key = "#p0.id")
    long insert(User user) throws DataAccessException;

    @CacheEvict(value = CACHE_PREFIX, key = "#p0.id")
    long update(User user) throws DataAccessException;

    @Cacheable(value = CACHE_PREFIX, key = "#p0")
    User queryById(Long id) throws DataAccessException;

    // 这个先不缓存
    User queryByUsername(String username) throws DataAccessException;
}