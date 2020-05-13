package com.lxq.blog.module.service;


import com.lxq.blog.module.pojo.Music;
import com.lxq.blog.utils.Page;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author lxq
 * @date 2020-5-12 15:25:25
 * @Version 1.0
 */
public interface MusicService {
    /**
     * 新增或者修改
     * @param music 音乐实体
     * @return Boolean 布尔
     */
    boolean saveOrUpdate(Music music);

    /**
     * 编号查询
     * @param id 编号
     * @return Music 音乐实体
     */
    Music queryById(Integer id);

    /**
     * 编号删除
     * @param id 编号
     * @return Boolean 布尔
     */
    boolean deleteById(Integer id);

    /**
     * 启动
     * @param id id 编号
     */
    void start(Integer id);

    /**
     * 禁用
     * @param id id 编号
     */
    void forbidden(Integer id);

    /**
     * 分页查询
     * @param page 分页对象
     * @return Page 分页对象
     */
    Page getList(Page page);

    /**
     * 前端显示
     * @return List 集合
     */
    List<Music> showList();

}
