package com.lxq.blog.module.controller;

import com.lxq.blog.exception.MyException;
import com.lxq.blog.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/testController")
public class TestController {

    @GetMapping(value = "/test/{testId}")
    public Result<Object> test(@PathVariable(value = "testId") Integer id) throws MyException {
        if (id==1) {
            return new Result("ok");
        }else {
            throw new MyException("发生了异常");
        }
    }
}
