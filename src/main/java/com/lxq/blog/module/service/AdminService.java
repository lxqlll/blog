package com.lxq.blog.module.service;


import com.lxq.blog.exception.MyException;
import com.lxq.blog.module.pojo.Admin;

/**
 * <p>
 * 管理员表服务层接口
 * </p>
 *
 * @author lxq
 * @date 2020年5月9日10:19:54
 * @Version 1.0
 */
public interface AdminService {
    /**
     * 登录方法
     * @param userName 用户名
     * @return Admin 管理员实体
     */
    Admin selectOne(String userName);

    /**
     * 查询管理员信息
     * @return Admin 管理员实体 不等于null查询成功
     */
    Admin getAdmin(Integer id);

    /**
     * 修改个人信息
     * @param admin 管理员实体
     */
    void updateAdminById(Admin admin) throws MyException;
}
