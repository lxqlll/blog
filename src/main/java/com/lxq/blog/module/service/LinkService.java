package com.lxq.blog.module.service;


import com.lxq.blog.exception.MyException;
import com.lxq.blog.module.pojo.Link;

import java.util.List;

/**
 * <p>
 *  友情链接实体类
 * </p>
 *
 * @author lxq
 * @date 2020年5月9日08:53:33
 * @Version 1.0
 */
public interface LinkService {
    /**
     * 添加
     * @param link 友情链接实体
     */
    void saveOrUpdate(Link link);

    /**
     * 删除
     * @param id 编号
     */
    void deleteLink(Integer id) throws MyException;

    /**
     * 根据id查询
     * @param id 编号
     * @return Link 友情链接实体
     */
    Link getLinkById(Integer id);

    /**
     * 查询所有
     * @return list集合
     */
    List<Link> getLink();
}
