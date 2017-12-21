package me.wcy.spring.app.kafka;

import java.io.Serializable;

/**
 * Created by hzwangchenyan on 2017/12/21.
 */
public class KafkaMessage implements Serializable {
    private Long id;
    private String msg;
    private long time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
