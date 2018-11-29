package com.rrx.kaoqins.core.config;

import org.springframework.context.annotation.Configuration;


/**
 * 开启分布式Session，只支持浏览器访问(cookies)
 */
//@EnableRedisHttpSession(redisNamespace=Const.CACHE_NAMESPACE_PREFIX)
@Configuration
public class RedisSessionConfig {
}