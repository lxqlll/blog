package com.lxq.blog.module.service;


import com.lxq.blog.module.pojo.User;
import com.lxq.blog.utils.Page;

/**
 * <p>
 * 用户表服务层接口
 * </p>
 *
 * @author lxq
 * @date 2020年5月12日16:30:14
 * @Version 1.0
 */
public interface UserService {

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



}
