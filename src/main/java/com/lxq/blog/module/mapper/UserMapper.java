package com.lxq.blog.module.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxq.blog.module.pojo.User;
import com.lxq.blog.utils.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 用户表Mapper
 * </p>
 *
 * @author lxq
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
@Component
public interface UserMapper extends BaseMapper<User> {
  Integer getCountByPage(@Param("page")Page page);
  List<User> getByPage(@Param("page")Page page);
}
