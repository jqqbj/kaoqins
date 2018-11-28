package com.rrx.kaoqins.core.log;

import java.lang.annotation.*;

/**
 * @Author JQQ
 * @Date 2018/11/28 15:07
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    String value();
}
