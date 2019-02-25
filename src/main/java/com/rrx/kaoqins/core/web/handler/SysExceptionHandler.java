package com.rrx.kaoqins.core.web.handler;

import com.rrx.kaoqins.core.enums.ResultCodeEnum;
import com.rrx.kaoqins.core.exception.BaseException;
import com.rrx.kaoqins.core.exception.BusinessException;
import com.rrx.kaoqins.core.web.model.ResultModel;
import com.rrx.kaoqins.core.web.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

/**
 * 异常处理器
 *
 * @author wanyong
 * @date 2017/11/12 16:07
 */
@Slf4j
@RestControllerAdvice
public class SysExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(Exception.class)
    public ResultModel exceptionHandler(Exception e) {
        log.info(e.getMessage(), e);
        if (e instanceof HttpMediaTypeNotSupportedException) {
            return ResultUtil.fail(ResultCodeEnum.NO_SUPPORTED_MEDIATYPE);
        }
        if(e instanceof HttpRequestMethodNotSupportedException){
            return ResultUtil.fail(ResultCodeEnum.NO_SUPPORTED_METHOMD);
        }
        // springboot参数验证框架如果验证失败则抛出MethodArgumentNotValidException异常
        if(e instanceof MethodArgumentNotValidException
                ||e instanceof MethodArgumentTypeMismatchException
                ||e instanceof IllegalArgumentException) {
            return ResultUtil.fail(ResultCodeEnum.BAD_REQUEST, e.getMessage());
        }
        if(e instanceof BindException ){
            List<ObjectError> errors = ((BindException) e).getAllErrors();
            StringBuilder sb = new StringBuilder();
            errors.forEach(p ->{
                FieldError fieldError = (FieldError) p;
                sb.append(fieldError.getDefaultMessage()+"<br/>");
            });
            return ResultUtil.fail(ResultCodeEnum.BAD_REQUEST, sb.toString());
        }
        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            return ResultUtil.fail(baseException.getCode(), baseException.getMessage());
        }
        if (e instanceof RuntimeException && e.getMessage().contains(BusinessException.class.getName())) {
            String message = e.getMessage().substring(BusinessException.class.getName().length() + 1, e.getMessage().indexOf("\r\n")).trim();
            return ResultUtil.fail(ResultCodeEnum.INTERNAL_SERVER_ERROR, message);
        }
        return ResultUtil.fail(ResultCodeEnum.INTERNAL_SERVER_ERROR);
    }



}
