package com.rrx.kaoqins.core.exception;


import com.rrx.kaoqins.core.global.Const;

/**
 * 登录异常类
 */
public class LoginException extends BaseException {

    public LoginException() {
        super();
    }

    public LoginException(Throwable ex) {
        super(ex);
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable ex) {
        super(message, ex);
    }

    @Override
    public Const.ResultCodeEnum getCode() {
        return Const.ResultCodeEnum.LOGIN_FAIL;
    }
}
