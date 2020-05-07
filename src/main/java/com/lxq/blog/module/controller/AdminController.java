package com.lxq.blog.module.controller;

import com.lxq.blog.enums.ResultEnum;
import com.lxq.blog.module.pojo.Admin;
import com.lxq.blog.utils.Result;
import com.lxq.blog.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @remark 管理员控制器
 * @author lxq
 * @createTime 2020年5月7日11:51:07
 * @version 1.0
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    /**
     * 登录
     * @param admin
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody Admin admin){
        //获取用户名
        String userName = admin.getUsername();
        //获取密码
        String password = admin.getPassword();
        //参数验证
        if (admin==null || StringUtils.isBlank(userName) || StringUtils.isBlank(password))
            return new Result(ResultEnum.PARAMS_NULL.getCode(),"参数为空");
        //获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        //实例化创建AuthenticationToken对象
        AuthenticationToken authenticationToken = new UsernamePasswordToken(userName,password);
        try {
            //执行登录方法
            subject.login(authenticationToken);
        } catch (AuthenticationException e) {
            return new Result(ResultEnum.ERROR.getCode(),"用户名或者密码错误");
        }
        //获取唯一标识
        Serializable serializable = subject.getSession().getId();
        //实例化创建map集合
        Map<String,Object> resultMap = new HashMap<>(2);
        resultMap.put("token",serializable);
        return new Result(resultMap);
    }

}
