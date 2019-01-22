package com.rrx.kaoqins.core.enums;


/**
 * 返回码枚举
 */
public enum ResultCodeEnum {
    SUCCESS(200, "成功"),
    INTERNAL_SERVER_ERROR(500, "服务器出错"),
    BAD_REQUEST(400, "请求参数出错"),
    NO_SUPPORTED_MEDIATYPE(415, "不支持的媒体类型,请使用application/json;charset=UTF-8"),
    NO_SUPPORTED_METHOMD(416, "不支持的提交方式"),
    LOGIN_FAIL(303, "登录失败"),
    LOGIN_FAIL_ACCOUNT_LOCKED(304, "用户被锁定"),
    LOGIN_FAIL_ACCOUNT_UNKNOWN(307, "不存在该用户"),
    LOGIN_FAIL_INCORRECT_CREDENTIALS(308, "密码不正确"),
    LOGIN_FAIL_CAPTCHA_ERROR(309, "验证码错误"),
    UNLOGIN(401, "没有登录"),
    UNAUTHORIZED(403, "没有权限"),
    DATA_DUPLICATE_KEY(601, "数据重复");

    private final int value;
    private final String message;

    ResultCodeEnum(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int value() {
        return this.value;
    }

    public String getMessage() {
        return this.message;
    }
}
