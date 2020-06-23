package com.lxq.blog.module.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxq.blog.excel.entity.ExportParams;
import com.lxq.blog.excel.handler.ExcelExportHandler;
import com.lxq.blog.module.mapper.LogMapper;
import com.lxq.blog.module.pojo.Log;
import com.lxq.blog.module.service.LogService;
import com.lxq.blog.utils.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 接口访问日志表服务实现类
 * </p>
 *
 * @author lxq
 * @date 2020年5月7日14:41:02
 * @Version 1.0
 *
 */
@Service
@Slf4j
public class LogServiceImpl implements LogService {

    //声明LogMapper对象
    @Autowired
    private LogMapper logMapper;

    @Override
    public void save(Log log){
        logMapper.insert(log);
    }

    @Override
    public Page<Log> getByPage(Page page) {
        //查询所有数据
        List<Log> Logs = logMapper.getByPage(page);
        //将数据放入page的集合中
        page.setList(Logs);
        //查询数据记录
        int count = logMapper.getCountByPage(page);
        //将数据放入page中
        page.setTotalCount(count);
        return page;
    }

    @Override
    public void delete(Integer id) {
        logMapper.deleteById(id);
    }

    @Override
    public void batchDelete(List<Integer> ids) {
        logMapper.deleteBatchIds(ids);
    }

    @Override
    public Workbook export() {
        String  [] clounm= { "log_id", "log_url", "log_status", "log_message",
                "log_method", "log_time", "log_ip","created_time"};
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select(clounm);
        List<Log> logList = logMapper.selectList(null);
        return new ExcelExportHandler().createSheet(new ExportParams("最新日志", "sheet1"), Log.class, logList);
    }

}
