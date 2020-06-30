package com.lxq.blog.module.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxq.blog.enums.ResultEnum;
import com.lxq.blog.exception.MyException;
import com.lxq.blog.module.mapper.UserMapper;
import com.lxq.blog.module.pojo.User;
import com.lxq.blog.module.service.UserService;
import com.lxq.blog.utils.Md5Utils;
import com.lxq.blog.utils.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
public class UserServiceImpl  implements UserService {

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

    @Override
    public void updateBatchById(List<Integer> userIdList) {
        List<User> list = userMapper.getListByIds(userIdList);
//        List<User> userIds = userIdList.stream().map(e -> {
//            User user = new User();
//            user.setPassword(Md5Utils.toMD5("123456"));
//            user.setUserId(e);
//            return user;
//        }).collect(Collectors.toList());
//        userMapper.updateBatchById(userIds);
        list.forEach(e->{
            e.setPassword(Md5Utils.toMD5("123456"));
            saveOrUpdate(e);
        });
    }

    @Override
    public void insert(User user) throws MyException {
        //查询用户
        User u = userMapper.selectById(user.getUsername());
        //是否存在用户
        if(null != u){
            throw new MyException(ResultEnum.ERROR.getCode(),"用户以存在");
        }
        //调用新增方法
        this.saveOrUpdate(user);
    }

    @Override
    public User login(String username) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(true,"deleted",0);
        wrapper.eq(true,"username",username);
        return userMapper.selectOne(wrapper);
    }
}
