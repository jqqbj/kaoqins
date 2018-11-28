package com.rrx.kaoqins.core.web.model;

import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回结果类
 */
@NoArgsConstructor
public class ResultModel<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public ResultModel(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 状态码
     */
    public int code;

    /**
     * 描述
     */
    public String message;

    /**
     * 数据结果集
     */
    public T data;

}
