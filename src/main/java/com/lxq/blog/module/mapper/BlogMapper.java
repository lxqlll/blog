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
    /**
     * 分页查询
     * @param page  分页对象
     * @return list 集合 大小大于0执行成功
     */
    public List<BlogVo> getByPage(Page<BlogVo> page);
    /**
     * 总记录数
     * @param page 分页对象
     * @return Integer 整形
     */
    public Integer getCountByPage(Page<BlogVo> page);

    /**
     * 推荐阅读
     * @return
     */
    public  List<BlogVo> recomRead();

    /**
     * 查询时间轴
     * @return
     */
    List<BlogVo> getTimeLine();
}
