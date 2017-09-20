package me.wcy.learnspring.service.impl;

import me.wcy.learnspring.common.ResponseCode;
import me.wcy.learnspring.common.ServiceRuntimeException;
import me.wcy.learnspring.dao.UserDAO;
import me.wcy.learnspring.po.User;
import me.wcy.learnspring.service.TokenService;
import me.wcy.learnspring.service.UserService;
import me.wcy.learnspring.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;

/**
 * Created by hzwangchenyan on 2017/9/7.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private TokenService tokenService;

    @Override
    public void register(String username, String password, String phoneNumber, String nickname, String signature) throws ServiceRuntimeException {
        User origin = userDAO.queryByUsername(username);
        if (origin != null) {
            throw new ServiceRuntimeException(ResponseCode.GLOBAL_ILLEGAL_REQUEST, "user exist");
        }

        User user = new User();
        user.setUsername(username);
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes()).toUpperCase();
        user.setPassword(md5Password);
        user.setPhone_number(phoneNumber);
        user.setNickname(nickname);
        user.setSignature(signature);
        user.setDb_create_time(new Timestamp(System.currentTimeMillis()));
        user.setDb_update_time(new Timestamp(System.currentTimeMillis()));
        try {
            userDAO.insert(user);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new ServiceRuntimeException(ResponseCode.GLOBAL_SERVER_ERROR, "register error", e);
        }
    }

    @Override
    public UserVO login(String username, String password) throws ServiceRuntimeException {
        try {
            User user = userDAO.queryByUsername(username);
            if (user == null) {
                throw new ServiceRuntimeException(ResponseCode.GLOBAL_DATA_NOT_EXIST, "user not exist");
            }
            if (!StringUtils.equalsIgnoreCase(user.getPassword(), password)) {
                throw new ServiceRuntimeException(ResponseCode.USER_AUTH_FAILED, "username or password error");
            }

            String token = tokenService.createToken(user.getId());
            UserVO userVO = UserVO.newUserVO(user);
            userVO.setToken(token);
            return userVO;
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new ServiceRuntimeException(ResponseCode.GLOBAL_SERVER_ERROR, "login error", e);
        }
    }
}
