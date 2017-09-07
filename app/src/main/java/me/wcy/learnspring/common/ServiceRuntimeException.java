package me.wcy.learnspring.common;

/**
 * Created by hzwangchenyan on 2017/9/7.
 */
public class ServiceRuntimeException extends RuntimeException {

    public ServiceRuntimeException(String message) {
        super(message);
    }

    public ServiceRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceRuntimeException(Throwable cause) {
        super(cause);
    }
}
