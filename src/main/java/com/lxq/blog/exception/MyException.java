package com.lxq.blog.exception;

import com.lxq.blog.enums.ResultEnum;

/**
 * @remark 自定义异常
 * @author lxq
 * @createTime 2020年5月7日11:49:37
 * @version 1.0
 */
public class MyException extends Exception{

    private static final long serialVersionUID = 2450214686001409867L;

    private Integer errorCode = ResultEnum.ERROR.getCode();

    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.errorCode = resultEnum.getCode();
    }

    public MyException(ResultEnum resultEnum, Throwable throwable) {
        super(resultEnum.getMsg(), throwable);
        this.errorCode = resultEnum.getCode();
    }

    public MyException(Integer errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public MyException(String msg) {
        super(msg);
    }

    public MyException(Throwable throwable) {
        super(throwable);
    }

    public MyException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public Integer getErrorCode() {
        return errorCode;
    }

}
