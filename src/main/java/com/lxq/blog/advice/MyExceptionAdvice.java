package com.lxq.blog.advice;

import com.lxq.blog.exception.MyException;
import com.lxq.blog.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @remark 定义统一异常处理
 * @author lxq
 * @createTime 2020年5月7日11:51:07
 * @version 1.0
 */
@ControllerAdvice
@Slf4j
public class MyExceptionAdvice {

    /**
     * 异常管理
     * @param myException 自定义异常
     * @return Result 统一返回类型
     */
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result execptionHandler(MyException myException){
        log.error("[统一异常处理]",myException);
        return new Result(myException.getMessage(), myException.getErrorCode());
    }
}
