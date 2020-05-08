package com.lxq.blog.module.service.impl;


import com.lxq.blog.module.mapper.LogMapper;
import com.lxq.blog.module.pojo.Log;
import com.lxq.blog.module.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

}
