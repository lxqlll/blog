package com.lxq.blog.module.service;


import com.lxq.blog.exception.MyException;
import com.lxq.blog.module.pojo.User;
import com.lxq.blog.utils.Page;

import java.util.List;

/**
 * <p>
 * 用户表服务层接口
 * </p>
 *
 * @author lxq
 * @date 2020年5月12日16:30:14
 * @Version 1.0
 */
public interface UserService  {

    /**
     * 新增或者修改
     * @param user
     * @return
     */
    boolean saveOrUpdate(User user);

    /**
     * 编号查询
     * @param id
     * @return
     */
    User queryById(Integer id);

    /**
     * 编号删除
     * @param id
     * @return
     */
    boolean deleteById(Integer id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page selectPage(Page page);

    /**
     * 重置密码
     * @param userIdList 集合
     */
    void updateBatchById(List<Integer> userIdList);

    /**
     * 注册
     * @param user
     */
    void insert(User user) throws MyException;

    /**
     * 登录方法
     */
    User login(String username);
}
