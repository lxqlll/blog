package com.lxq.blog.module.service;


import com.lxq.blog.module.pojo.Log;

/**
 * <p>
 * 接口访问日志表服务层接口
 * </p>
 *
 * @author lxq
 * @date 2020年5月7日14:39:56
 * @Version 1.0
 *
 */
public interface LogService {
    /**
     * 新增接口访问日志
     * @param log 接口访问日志表实体类
     */
    public void save(Log log);
}
