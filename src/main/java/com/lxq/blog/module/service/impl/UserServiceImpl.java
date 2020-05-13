package com.lxq.blog.module.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxq.blog.module.mapper.UserMapper;
import com.lxq.blog.module.pojo.User;
import com.lxq.blog.module.service.UserService;
import com.lxq.blog.utils.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户表服务实现类
 * </p>
 *
 * @author lxq
 * @date 2020年5月12日16:31:07
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    /**
     * 声明UserMapper对象
     */
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean saveOrUpdate(User user) {
        boolean falg = false;
        User u = this.queryById(user.getUserId());
        if (null==u){
            if (userMapper.insert(user)>0){
                falg = true;
            }
        }else{
            Integer version = user.getVersion();
            user.setVersion(user.getVersion()+1);
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq(true,"deleted",0);
            wrapper.eq(true,"user_id",user.getUserId());
            wrapper.eq(true,"version",version);
            if (  userMapper.update(user,wrapper)>0){
                falg = true;
            }
        }
        return falg;
    }

    @Override
    public User queryById(Integer id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(true,"deleted",0);
        wrapper.eq(true,"user_id",id);
        return  userMapper.selectOne(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteById(Integer id) {
        User user = this.queryById(id);
        if(user==null)return false;
        user.setDeleted(1);
        return  saveOrUpdate(user);
    }

    @Override
    public Page selectPage(Page page) {
        page.setList(userMapper.getByPage(page));
        page.setTotalCount(userMapper.getCountByPage(page));
        return page;
    }
}
