package com.rrx.kaoqins.core.enums;

import static com.rrx.kaoqins.core.constant.CacheConsts.CACHE_NAMESPACE_PREFIX;

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
