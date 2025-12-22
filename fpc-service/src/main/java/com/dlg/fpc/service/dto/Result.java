package com.dlg.fpc.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回封装
 *
 * @author lingui
 * @Date 2022/3/15
 */
@Data
public class Result<T> implements Serializable {

    private static final int SUCCESS = 200;

    /**
     * 编码：200表示成功，其他值表示失败
     */
    private int code = SUCCESS;

    /**
     * 消息内容
     */
    private String msg = "success";

    /**
     * 响应数据
     */
    private T data;

    public boolean ifOk() {
        return code == SUCCESS;
    }

    public static Result<String> success() {
        return new Result<>();
    }

    public static Result<Object> error(int code, String msg) {
        Result<Object> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public static Result<Object> error(String message) {
        Result<Object> result = new Result<>();
        result.setCode(500);
        result.setMsg(message);
        result.setData(null);
        return result;
    }

    public static Result<Object> error(int code) {
        Result<Object> result = new Result<>();
        result.setCode(code);
        result.setMsg("error");
        result.setData(null);
        return result;
    }

    public Result<T> ok(T t) {
        Result<T> result = new Result<>();
        result.setData(t);
        return result;
    }

}