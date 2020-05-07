package com.lxq.blog.utils;

import com.lxq.blog.enums.ResultEnum;

import java.io.Serializable;

/**
 * @remark 定义返回类型
 * @author lxq
 * @createTime 2020年5月7日11:51:07
 * @version 1.0
 */
public class Result<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public Result(){
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
    }

    public Result(String msg){
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
    }

    public Result(T data){
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
        this.data = data;
    }

    public Result(String msg,T data){
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = msg;
        this.data = data;
    }

    public Result(String msg,Integer code){
        this.msg = msg;
        this.code = code;
    }

    public Result(ResultEnum resultEnum){
        this.msg = resultEnum.getMsg();
        this.code = resultEnum.getCode();
    }
}
