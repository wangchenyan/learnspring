package me.wcy.learnspring.model;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by hzwangchenyan on 2017/9/5.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private int code;
    private String message = "";
    private Object result;
    private Integer total;

    public Response(int code, String message) {
        this(code, message, null, null);
    }

    public Response(Object result) {
        this(result, null);
    }

    public Response(Object result, Integer total) {
        this(200, "success", result, total);
    }

    public Response(int code, String message, Object result, Integer total) {
        this.code = code;
        this.message = message;
        this.result = result;
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
