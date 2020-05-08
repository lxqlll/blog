package com.lxq.blog.module.service;


import com.lxq.blog.module.pojo.Admin;

/**
 * <p>
 * 管理员表服务层接口
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
public interface AdminService {
    /**
     *
     * @param userName
     * @return
     */
    Admin selectOne(String userName);
}
