package me.wcy.learnspring.po;

import java.sql.Timestamp;

/**
 * Created by hzwangchenyan on 2017/9/7.
 */
public class User {
    private Integer id;
    private String username;
    private Timestamp db_create_time;
    private Timestamp db_update_time;
    private String password;
    private String phone_number;
    private String nickname;
    private String signature;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getDb_create_time() {
        return db_create_time;
    }

    public void setDb_create_time(Timestamp db_create_time) {
        this.db_create_time = db_create_time;
    }

    public Timestamp getDb_update_time() {
        return db_update_time;
    }

    public void setDb_update_time(Timestamp db_update_time) {
        this.db_update_time = db_update_time;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}