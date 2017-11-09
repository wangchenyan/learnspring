package me.wcy.spring.app.common;

/**
 * Created by hzwangchenyan on 2017/9/7.
 */
public class ServiceRuntimeException extends RuntimeException {
    private int code = ResponseCode.GLOBAL_SERVER_ERROR;

    public ServiceRuntimeException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceRuntimeException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
