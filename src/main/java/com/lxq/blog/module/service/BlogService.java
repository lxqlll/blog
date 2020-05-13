package com.lxq.blog.module.service;


import com.lxq.blog.module.pojo.Blog;
import com.lxq.blog.utils.Page;
import com.lxq.blog.vo.BlogVo;

/**
 * <p>
 * 博客表服务层接口
 * </p>
 *
 * @author lxq
 * @date 2020年5月9日14:40:03
 * @Version 1.0
 */
public interface BlogService {
    /**
     * 新增/修改方法
     * @param blog
     */
    void saveOrUpdate(Blog blog);

    /**
     * 根据编号查询
     * @param id 编号
     * @return Blog 博客表实体 不等于空查询成功
     */
    Blog findBlogById(String id);

    /**
     * 查询阅读数
     * @param id 编号
     * @return BlogVo 博客表类型表实体 不等于空查询成功
     */
    BlogVo readById(String id);

    /**
     * 删除
     * @param id 编号
     */
    void deleteBlog(String id);

    /**
     * 分页查询
     */
    Page<BlogVo> getByPage(Page<BlogVo> page);
}
