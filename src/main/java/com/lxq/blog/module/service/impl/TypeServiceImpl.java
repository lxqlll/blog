package com.lxq.blog.module.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxq.blog.module.mapper.TypeMapper;
import com.lxq.blog.module.pojo.Type;
import com.lxq.blog.module.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 * 帖子类型表服务实现类
 * </p>
 *
 * @author lxq
 * @date 2020年5月9日14:11:28
 * @Version 1.0
 */
@Service
@Slf4j
public class TypeServiceImpl implements TypeService {
    /**
     * 声明TypeMapper对象
     */
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public boolean saveOrUpdate(Type type) {
        boolean falg = false;
        //根据名称来查询帖子
        Type t = queryById(type.getTypeId());
        if(null != t){  //判断是否有数据
            //实例化创建QueryWrapper对象
            QueryWrapper queryWrapper =  new QueryWrapper<>()
                    .eq("deleted",0).eq("type_id",type.getTypeId());
            if(typeMapper.update(type,queryWrapper)>0){ //执行修改方法
                falg = true;
            }else {
                falg = false;
            }
        }else {
            if(typeMapper.insert(type)>0){  //执行新增方法
                falg = true;
            }else {
                falg = false;
            }
        }
        return falg;
    }

    @Override
    public Type queryTypeByName(String name) {
        //实例化创建QueryWrapper对象
        QueryWrapper queryWrapper = new QueryWrapper();
        //添加查询条件
        queryWrapper.eq("deleted",0);
        queryWrapper.eq("type_name",name);
        //执行查询一个方法
        Type t = typeMapper.selectOne(queryWrapper);
        return t ;
    }

    @Override
    public List<Type> showAllType() {
        //实例化创建QueryWrapper对象
        QueryWrapper queryWrapper = new QueryWrapper();
        //添加查询条件
        queryWrapper.eq("deleted",0);
        queryWrapper.eq("enable",1);
        return typeMapper.selectList(queryWrapper);
    }

    @Override
    public List<Type> queryAllType() {
        //实例化创建QueryWrapper对象
        QueryWrapper queryWrapper = new QueryWrapper();
        //添加查询条件
        queryWrapper.eq("deleted",0);
        return typeMapper.selectList(queryWrapper);
    }

    @Override
    public void updateById(Type type) {
        typeMapper.updateById(type);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteById(Integer id) {
        Type t = queryById(id); //调用根据编号查询方法
        if(null==t){
            return false;
        }else{
            Type type = new Type();
            type.setDeleted(1);
            type.setTypeId(id);
            typeMapper.updateById(type);    //调用修改方法
            return true;
        }
    }

    @Override
    public Type queryById(Integer id) {
        //实例化创建QueryWrapper对象
        QueryWrapper queryWrapper = new QueryWrapper();
        //添加条件
        queryWrapper.eq("type_id",id);
        queryWrapper.eq("deleted",0);
        return  typeMapper.selectOne(queryWrapper);
    }

    @Override
    public int updateTypeStart(Integer id) {
        int result = 0;
        //调用根据编号查询方法
        Type type = queryById(id);
        //判断有无数据
        if(null==type){

        }else{
            type.setEnable(0);
            typeMapper.updateById(type); //修改状态
            result = 1;
        }
        return result;
    }

    @Override
    public int updateTypeBlock(Integer id) {
        int result = 0;
        //调用根据编号查询方法
        Type type = queryById(id);
        //判断有无数据
        if(null==type){

        }else{
            type.setEnable(1);
            typeMapper.updateById(type); //修改状态
            result = 1;
        }
        return result;
    }

    @Override
    public int showAllTypeCout() {
        //实例化创建QueryWrapper对象
        QueryWrapper queryWrapper = new QueryWrapper();
        //添加查询条件
        queryWrapper.eq("deleted",0);
        queryWrapper.eq("enable",1);
        return typeMapper.selectCount(queryWrapper);
    }
}
