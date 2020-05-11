package com.lxq.blog.module.service;

import com.lxq.blog.exception.MyException;
import com.lxq.blog.module.pojo.About;

/**
 * <p>
 *  闲言服务层
 * </p>
 *
 * @author lxq
 * @date 2020年5月11日15:56:23
 * @Version 1.0
 */
public interface AboutService {
    /**
     * 新增或者修改方法
     * @param about 闲言实体
     */
    void saveOrUpdate(About about) throws MyException;

    /**
     * 根据编号查询闲言
     * @param id 编号
     * @return About 闲言实体
     */
    About getAboutById(Integer id);

    /**
     * 阅读方法
     * @param id 编号
     */
    About readingAbout(Integer id);

    /**
     * 删除方法
     * @param id 编号
     */
    void deleteAbout(Integer id);

    /**
     * 启用方法
     * @param id 编号
     */
    void start(Integer id);

    /**
     * 禁用方法
     * @param id 编号
     */
    void disable(Integer id);

    /**
     *
     * @param id
     * @param state
     * @return
     */
    int updateOne(Integer id,Integer state);
}
