package com.rrx.kaoqins.core.global;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统通用常量
 */
public final class Const {

    /**
     * 当前用户
     */
    public static final String CURRENT_USER = "CURRENT_USER";
    /**
     * 当前用户
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * 缓存键值
     */
    public static final Map<Class<?>, String> CACHE_KEY_MAP = new HashMap<>(5);

    /**
     * 缓存命名空间前缀
     */
    public static final String CACHE_NAMESPACE_PREFIX = "jdb:";

    /**
     * 模块缓存名
     */
    public class CacheName {
        public static final String DICT = "dict";
        public static final String PARAM = "param";
    }

    /**
     * 缓存命名空间枚举
     */
    public enum CacheNamespaceEnum {
        DATA(CACHE_NAMESPACE_PREFIX + "data:", "数据缓存"),
        LOCK(CACHE_NAMESPACE_PREFIX + "lock:", "分布式锁"),
        CAPTCHA(CACHE_NAMESPACE_PREFIX + "captcha:", "验证码");

        private String value;
        private String message;

        CacheNamespaceEnum(String value, String message) {
            this.value = value;
            this.message = message;
        }

        public String value() {
            return this.value;
        }

        public String getMessage() {
            return this.message;
        }
    }

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

    /**
     * 日志操作类型枚举
     */
    public enum LogOptEnum {
        QUERY(0, "查询"),
        ADD(1, "新增"),
        MODIFY(2, "修改"),
        DELETE(3, "删除"),
        LOGIN(4, "登录"),
        UNKNOW(9, "未知");
        private final int value;
        private final String message;

        LogOptEnum(int value, String message) {
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

}
