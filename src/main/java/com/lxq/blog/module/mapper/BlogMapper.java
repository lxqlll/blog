package com.lxq.blog.module.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxq.blog.module.pojo.Blog;
import com.lxq.blog.utils.Page;
import com.lxq.blog.vo.BlogVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 博客表Mapper
 * </p>
 *
 * @author lxq
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
@Component
public interface BlogMapper extends BaseMapper<Blog> {

    public List<BlogVo> getByPage(Page<BlogVo> page);

    public Integer getCountByPage(Page<BlogVo> page);
}
