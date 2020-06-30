package com.lxq.blog.utils;

import com.lxq.blog.module.pojo.Admin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.stereotype.Component;

/**
 * Shiro相关工具类
 *
 * @Author: lxq
 * @Date: 2020年5月7日17:10:05
 * @Version 1.0
 */
@Component
public class ShiroUtils {

    private ShiroUtils() {
    }


    /**
     * 切换身份，登录后，动态更改subject的用户属性
     * @param adminInfo
     */
    public static void setAdmin(Admin adminInfo) {
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realmName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection =
                new SimplePrincipalCollection(adminInfo, realmName);
        subject.runAs(newPrincipalCollection);
    }

    /**
     * 获取登录中的用户
     *
     * @return
     */
    public static Object getLoginUser() {
        Session session = SecurityUtils.getSubject().getSession();
        SimplePrincipalCollection principalCollection =
                (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if (principalCollection == null) {
            return null;
        }//获取当前用户
        return principalCollection.getPrimaryPrincipal();
    }
}
