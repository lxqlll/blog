package com.lxq.blog.module.service;


import com.lxq.blog.module.pojo.Log;
import com.lxq.blog.utils.Page;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

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

    /**
     * 分页查询
     * @param page 分页
     * @return Log 日志
     */
    Page<Log> getByPage(Page page);

    /**
     * 删除方法
     * @param id 编号
     */
    void delete(Integer id);

    /**
     * 批量删除
     * @param ids 集合
     */
    void batchDelete(List<Integer> ids);

    /**
     * 全部数据导入excel
     * @return
     */
    Workbook export();
}
