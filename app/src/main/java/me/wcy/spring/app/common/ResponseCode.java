package me.wcy.spring.app.common;

/**
 * Created by hzwangchenyan on 2017/9/7.
 */
public interface ResponseCode {
    int GLOBAL_SUCCESS = 200;

    int GLOBAL_PARAM_ERROR = 8001;
    int GLOBAL_SERVER_ERROR = 8002;
    int GLOBAL_DATA_NOT_EXIST = 8003;
    int GLOBAL_ILLEGAL_REQUEST = 8004;

    int USER_AUTH_FAILED = 8101;
}
