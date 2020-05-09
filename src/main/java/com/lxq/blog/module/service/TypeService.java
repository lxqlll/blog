package com.lxq.blog.module.service;


import com.lxq.blog.module.pojo.Type;

import java.util.List;

/**
 * <p>
 * 帖子类型表服务层接口
 * </p>
 *
 * @author lxq
 * @date 2020年5月9日08:45:22
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
