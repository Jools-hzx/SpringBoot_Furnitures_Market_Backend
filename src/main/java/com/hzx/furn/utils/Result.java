package com.hzx.furn.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jools He
 * @version 1.0
 * @date 2024/5/5 12:15
 * @description: TODO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("all")
public class Result<T> {

    private String code;    //返回的状态码
    private String msg; //返回的消息
    private T data; //携带的数据

    public Result(T data) {
        this.data = data;
    }

    //创建成功消息
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setCode("200");
        result.setMsg("请求成功");
        result.setData(data);
        return result;
    }

    //创建失败消息-服务器内部异常
    public static Result failServerError() {
        Result result = new Result<>();
        result.setCode("5xx");
        result.setMsg("请求失败,Server Internal Error!!!");
        return result;
    }

    //失败消息 - 客户端请求异常
    public static Result failClientError() {
        Result result = new Result<>();
        result.setCode("4xx");
        result.setMsg("请求失败,Client Error!!!");
        return result;
    }
}
