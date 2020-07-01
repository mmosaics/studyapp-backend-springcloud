package org.example.util;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义类型，用于返回结果
 *          分为状态码，消息和数据
 *
 *          状态码类型如下：
 *          200：成功
 *          500：错误
 *          501：bean错误
 *          502：token出错
 *          555：异常错误
 */

@Setter
@Getter
public class GlobalResult {

    // response status
    private Integer status;


    // response message;
    private String msg;

    // response data
    private Object data;

    private String ok;

    public static GlobalResult build(Integer status, String msg, Object data) {
        return new GlobalResult(status, msg, data);
    }

    public static GlobalResult ok(Object data) {
        return new GlobalResult(data);
    }

    public static GlobalResult ok() {
        return new GlobalResult(null);
    }

    public static GlobalResult errorMsg(String msg) {
        return new GlobalResult(500, msg, null);
    }

    public static GlobalResult errorMap(Object data) {
        return new GlobalResult(501, "error", data);
    }

    public static GlobalResult errorTokenMsg(String msg) {
        return new GlobalResult(502, msg, null);
    }

    public static GlobalResult errorException(String msg) {
        return new GlobalResult(555, msg, null);
    }

    public GlobalResult() {

    }

    public GlobalResult(Integer status, String msg, Object data)
    {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public GlobalResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }
}
