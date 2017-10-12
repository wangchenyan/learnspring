package me.wcy.learnspring.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import me.wcy.learnspring.entity.User;

/**
 * Created by hzwangchenyan on 2017/9/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVO {
    private Long id;
    private String username;
    private String phone_number;
    private String nickname;
    private String signature;
    private String token;

    public static UserVO newUserVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUsername(user.getUsername());
        userVO.setPhone_number(user.getPhone_number());
        userVO.setNickname(user.getNickname());
        userVO.setSignature(user.getSignature());
        return userVO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
