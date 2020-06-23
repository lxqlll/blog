package com.lxq.blog.interceptor;

import com.lxq.blog.enums.ResultEnum;
import com.lxq.blog.exception.MyException;
import com.lxq.blog.module.pojo.Admin;
import com.lxq.blog.utils.ShiroUtils;
import com.lxq.blog.utils.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @remark 登录拦截器
 * @author lxq
 * @createTime 2020年5月7日16:28:45
 * @version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {

//    //友情链接
//        filterMap.put("/link/getLink", "anon");
//        filterMap.put("/link/getLinkById/*", "anon");
//    //音乐欣赏
//        filterMap.put("/music/page", "anon");
//        filterMap.put("/music/list", "anon");
//    //关于
//        filterMap.put("/about/*", "anon");
//
//        filterMap.put("/login/getAdmin", "anon");


    private static String []  whiteList = {"/link/getLink"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(containsWhiteList(request.getRequestURI())){
            return  true;
        }
        //获取token
        String token = request.getHeader("Authorization");
        //判断token是非为控
        if(!StringUtils.isBlank(token)){
            Admin admin = (Admin) ShiroUtils.getLoginUser();
            if (admin!=null){
                return  true;
            }
        }

       throw new MyException(ResultEnum.NOT_LOGIN.getCode(),"当前账号未登录");
    }

    /**
     * 判断是否需要拦截
     * @param s
     * @return
     */
    private boolean containsWhiteList(String s){
        for (String url : whiteList) {
            if (url.equals(s.toLowerCase())){
                return true;
            }
        }
        return false;
    }

}
