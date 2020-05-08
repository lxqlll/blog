package com.lxq.blog.realm;

import com.lxq.blog.enums.ResultEnum;
import com.lxq.blog.exception.MyException;
import com.lxq.blog.module.pojo.Admin;
import com.lxq.blog.module.service.AdminService;
import lombok.SneakyThrows;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @remark 管理员登录和授权逻辑
 * @author lxq
 * @createTime 2020年5月7日16:28:45
 * @version 1.0
 */
public class AdminRealm extends AuthorizingRealm {

    //声明AdminMapper对象
    @Autowired
    private AdminService adminService;


    /**
     * 授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    /**
     * 登录方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //得到UsernamePasswordToken对象
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        //获取用户名
        String username = usernamePasswordToken.getUsername();
        //调用登录方法
        Admin admin = adminService.selectOne(username);
        //判断是否有数据
        if (admin==null){
            throw new MyException(ResultEnum.ERROR.getCode(),"用户不存在");
        }
        return new SimpleAuthenticationInfo(admin,admin.getPassword(),this.getName());
    }
}
