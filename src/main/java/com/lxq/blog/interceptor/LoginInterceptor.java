package com.lxq.blog.interceptor;

import com.lxq.blog.enums.ResultEnum;
import com.lxq.blog.exception.MyException;
import com.lxq.blog.module.pojo.Admin;
import com.lxq.blog.utils.ShiroUtils;
import com.lxq.blog.utils.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取token
        String token = request.getHeader("Authorization");

        if(StringUtils.isBlank(token)){
            Admin admin = (Admin) ShiroUtils.getLoginUser();
            if (admin!=null){
                return  true;
            }
        }
       throw new MyException(ResultEnum.NOT_LOGIN.getCode(),"当前账号未登录");
    }
}
