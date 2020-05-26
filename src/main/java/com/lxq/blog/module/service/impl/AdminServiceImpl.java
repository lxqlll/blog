package com.lxq.blog.module.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxq.blog.exception.MyException;
import com.lxq.blog.module.mapper.AdminMapper;
import com.lxq.blog.module.pojo.Admin;
import com.lxq.blog.module.service.AdminService;
import com.lxq.blog.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

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

    @Override
    public Admin getAdmin(Integer id) {
        //实例化创建QueryWrapper对象
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id",id);
        return adminMapper.selectOne(wrapper);
    }

    @Override
    public void updateAdminById(Admin admin) throws MyException {
        Admin a = getAdmin(admin.getId()); //调用id查询方法
        if (null == a){//判断有无数据
            throw new MyException("修改失败,未查询到数据");
        }else {
            if(StringUtils.isBlank(admin.getPassword())){
                admin.setPassword(a.getPassword());
            }else if(!StringUtils.isBlank(admin.getPassword()) && admin.getId()!=null){
                logger.info("[我只改密码]==>测试");
            }
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("id",admin.getId());
            adminMapper.update(admin,queryWrapper);
        }
    }
}
