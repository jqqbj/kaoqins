package com.rrx.kaoqins.core.constant;

import java.util.HashMap;
import java.util.Map;

public class CacheConsts {
    /**
     * 缓存键值
     */
    public static final Map<Class<?>, String> CACHE_KEY_MAP = new HashMap<>(5);
    /**
     * 缓存命名空间前缀
     */
    public static final String CACHE_NAMESPACE_PREFIX = "rrx:";
    public static final String CACHE_DICT = "dict";
    public static final String CACHE_PARAM = "param";
}
