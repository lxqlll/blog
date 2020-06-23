package com.lxq.blog.module.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxq.blog.module.pojo.User;
import com.lxq.blog.utils.Page;
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
  /**
   * 总记录数
   * @param page 分页对象
   * @return Integer 整形
   */
  Integer getCountByPage(Page page);

  /**
   * 分页查询
   * @param page  分页对象
   * @return list 集合 大小大于0执行成功
   */
  List<User> getByPage(Page page);

  /**
   * 重置密码
   * @param ids 集合
   * @return  list 集合 大小大于0执行成功
   */
  List<User> getListByIds(List<Integer> ids);
}
