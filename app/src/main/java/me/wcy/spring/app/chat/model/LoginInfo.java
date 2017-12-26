package me.wcy.spring.app.chat.model;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by hzwangchenyan on 2017/12/26.
 */
public class LoginInfo implements Serializable {
    private String account;
    private String token;
    private int result;

    @Override
    public boolean equals(Object obj) {
        return obj instanceof LoginInfo
                && ((LoginInfo) obj).account != null
                && ((LoginInfo) obj).account.equals(this.account)
                && ((LoginInfo) obj).token != null
                && ((LoginInfo) obj).token.equals(this.token);
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
