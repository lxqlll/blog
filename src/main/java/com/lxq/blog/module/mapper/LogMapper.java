package com.lxq.blog.module.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxq.blog.module.pojo.Log;
import com.lxq.blog.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 接口访问日志表Mapper
 * </p>
 *
 * @author lxq
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
@Component
public interface LogMapper extends BaseMapper<Log> {
    /**
     * 分页查询
     * @param page 分页
     * @return List 集合
     */
    List<Log> getByPage(Page page);

    /**
     * 分页查询总记录书
     * @param page 分页对象
     * @return Integer 整形
     */
    Integer getCountByPage(Page page);
}
