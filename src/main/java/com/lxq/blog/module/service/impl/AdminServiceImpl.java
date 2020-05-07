package com.lxq.blog.module.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxq.blog.module.mapper.AdminMapper;
import com.lxq.blog.module.pojo.Admin;
import com.lxq.blog.module.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表服务实现类
 * </p>
 *
 * @author lxq
 * @date 2020年5月7日16:36:06
 * @Version 1.0
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    //声明AdminMapper对象
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin selectOne(String userName) {
        //实例化创建QueryWrapper对象
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username",userName);
        //根据用户名和密码查询
        return adminMapper.selectOne(wrapper);
    }
}
