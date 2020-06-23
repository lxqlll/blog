package com.lxq.blog.config;

import com.lxq.blog.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * mvc拦截器
 * @Author: lxq
 * @Date: 2020年5月7日17:11:02
 * @Version 1.0
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    /**
     * 自定义拦截注入
     * @return LoginInterceptor 拦截除登录以为所有的路径
     */
    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    /**
     * 配置拦截器
     * @param registry 拦截器注册
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //添加拦截
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**").excludePathPatterns("/*/login");
        super.addInterceptors(registry);
    }
}
