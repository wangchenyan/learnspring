package me.wcy.spring.remote.zipkin;

/**
 * Created by hzwangchenyan on 2017/11/9.
 */
public enum StatusEnum {
    OK(200, "OK"),
    ERROR(500, "ERROR");

    private int code;
    private String desc;

    StatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
