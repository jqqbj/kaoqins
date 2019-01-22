package com.rrx.kaoqins.core.exception;


import com.rrx.kaoqins.core.enums.ResultCodeEnum;

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
    public ResultCodeEnum getCode() {
        return ResultCodeEnum.INTERNAL_SERVER_ERROR;
    }
}
