package me.wcy.spring.app.chat;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by hzwangchenyan on 2017/12/26.
 */
public class CMessage implements Serializable {
    private String from;
    private String to;
    private int type;
    private String content;

    public String toJson() {
        return JSON.toJSONString(this);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
