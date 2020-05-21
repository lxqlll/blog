package com.lxq.blog.module.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxq.blog.module.pojo.About;
import com.lxq.blog.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 管理员表Mapper
 * </p>
 *
 * @author lxq
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Component
public interface AboutMapper extends BaseMapper<About> {

    /**
     * 分页查询
     * @param page  分页对象
     * @return list 集合 大小大于0执行成功
     */
    List<About> getByPage(Page page);

    /**
     * 总记录数
     * @param page 分页对象
     * @return Integer 整形
     */
    Integer getCountByPage(Page page);


}
