package com.rrx.kaoqins.core.web.util;

import cn.hutool.core.util.StrUtil;
import com.rrx.kaoqins.core.enums.ResultCodeEnum;
import com.rrx.kaoqins.core.web.model.ResultModel;

/**
 * 返回结果工具类
 */
public class ResultUtil {

    public static ResultModel ok() {
        return ok(null);
    }

    public static ResultModel ok(Object object) {
        return new ResultModel(ResultCodeEnum.SUCCESS.value(),
                ResultCodeEnum.SUCCESS.getMessage(), object);
    }

    public static ResultModel fail(ResultCodeEnum resultCodeEnum) {
        return new ResultModel(resultCodeEnum.value(), resultCodeEnum.getMessage(), null);
    }

    public static ResultModel fail(int code, String message) {
        return new ResultModel(code, message, null);
    }

    public static ResultModel fail(ResultCodeEnum resultCodeEnum, String message) {
        return new ResultModel(resultCodeEnum.value(), StrUtil.isBlank(message) ? resultCodeEnum.getMessage() : message, null);
    }
}
