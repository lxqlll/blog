package com.lxq.blog.module.service;


import com.lxq.blog.module.pojo.Type;

import java.util.List;

/**
 * <p>
 * 帖子类型表服务层接口
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
public interface TypeService {
    public boolean saveOrUpdate(Type type);
    public Type queryTypeByName(String typeName);
    public List<Type> showAllType();
    public List<Type> queryAllType();
    public void updateById(Type type);
    public boolean deleteById(Integer id);
    public Type queryById(Integer id);
    public int updateTypeStart(Integer id);
    public int updateTypeBlock(Integer id);
}
