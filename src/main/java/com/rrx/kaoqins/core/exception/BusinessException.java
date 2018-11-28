package com.rrx.kaoqins.core.exception;


import com.rrx.kaoqins.core.global.Const;

/**
 * 业务异常类
 */
public class BusinessException extends BaseException {

    public BusinessException() {
        super();
    }

    public BusinessException(Throwable ex) {
        super(ex);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable ex) {
        super(message, ex);
    }

    @Override
    public Const.ResultCodeEnum getCode() {
        return Const.ResultCodeEnum.INTERNAL_SERVER_ERROR;
    }
}
