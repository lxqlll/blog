package com.lxq.blog.module.service;


import com.lxq.blog.enums.ResultEnum;
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
    /**
     * 新增或者修改帖子
     * @param type  帖子类型
     * @return boolean 布尔 返回true执行成功
     */
    public boolean saveOrUpdate(Type type);

    /**
     * 根据帖子名查询
     * @param typeName 帖子名
     * @return Type  帖子类型 不等于空表示查询成功
     */
    public Type queryTypeByName(String typeName);

    /**
     * 前端显示所有帖子类型
     * @return list 集合 集合大小大于1查询成功或者查询到数据
     */
    public List<Type> showAllType();

    /**
     * 后端显示所有帖子类型
     * @return list 集合 集合大小大于1查询成功或者查询到数据
     */
    public List<Type> queryAllType();

    /**
     * 根据id修改帖子类型
     * @param type  帖子类型实体
     */
    public void updateById(Type type);

    /**
     * 删除帖子类型
     * @param id 编号
     * @return boolean 布尔 返回true执行成功
     */
    public boolean deleteById(Integer id);

    /**
     * 根据id查询帖子类型
     * @param id 编号
     * @return Type  帖子类型 不等于空表示查询成功
     */
    public Type queryById(Integer id);

    /**
     * 开启帖子类型
     * @param id 编号
     * @return int 整形 返回结果大于1执行成功
     */
    public int updateTypeStart(Integer id);

    /**
     * 停用帖子
     * @param id 编号
     * @return int 整形 返回结果大于1执行成功
     */
    public int updateTypeBlock(Integer id);

    /**
     * 数量
     * @return
     */
    int showAllTypeCout();
}
