package com.noobug;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表示调用结果的类
 *
 * @param <T> 携带的数据类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    protected static final String DEFAULT_SUCCESS_MSG = "成功";
    protected static final String DEFAULT_ERROR_MSG = "失败";

    protected Integer code;

    protected Boolean success;

    protected String msg;

    protected T data;

    public static <T> Result<T> ok(String msg, T t) {
        return new Result<>(0, true, msg, t);
    }

    public static <T> Result<T> ok(T t) {
        return Result.ok(DEFAULT_SUCCESS_MSG, t);
    }

    public static <T> Result<T> ok() {
        return Result.ok(null);
    }

    public static <T> Result<T> error(Integer code, String msg, T t) {
        return new Result<>(code, false, msg, t);
    }

    public static <T> Result<T> error(Integer code, String msg) {
        return Result.error(code, msg, null);
    }

    public static <T> Result<T> error(Integer code) {
        return Result.error(code, DEFAULT_ERROR_MSG, null);
    }
}