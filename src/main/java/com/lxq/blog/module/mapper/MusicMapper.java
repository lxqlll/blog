package com.lxq.blog.module.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxq.blog.module.pojo.Music;
import com.lxq.blog.utils.Page;
import org.apache.ibatis.annotations.Param;
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
public interface MusicMapper extends BaseMapper<Music> {
    /**
     * 分页查询
     * @param page 分页对象
     * @return list 集合
     */
    List<Music> getList(@Param("page")Page page);

    /**
     * 分页总记录数
     * @param page 分页对象
     * @return Integer 整形
     */
    Integer getCountByPage(@Param("page")Page page);
}
