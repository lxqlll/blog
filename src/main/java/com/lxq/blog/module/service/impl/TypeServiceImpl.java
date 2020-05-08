package com.lxq.blog.module.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxq.blog.module.mapper.TypeMapper;
import com.lxq.blog.module.pojo.Type;
import com.lxq.blog.module.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 帖子类型表服务实现类
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Service
@Slf4j
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;


    @Override
    public boolean saveOrUpdate(Type type) {
        boolean falg = false;
        Type t = queryTypeByName(type.getTypeName());
        if(null != t){
            QueryWrapper queryWrapper =  new QueryWrapper<>().eq("type_name",type.getTypeName());
            if(typeMapper.update(type,queryWrapper)>0){
                falg = true;
            }else {
                falg = false;
            }
        }else {
            if(typeMapper.insert(type)>0){
                falg = true;
            }else {
                falg = false;
            }
        }
        return falg;
    }

    @Override
    public Type queryTypeByName(String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("deleted",0);
        queryWrapper.eq("type_name",name);
        Type t = typeMapper.selectOne(queryWrapper);
        return t ;
    }

    @Override
    public List<Type> showAllType() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("deleted",0);
        queryWrapper.eq("enable",1);
        return typeMapper.selectList(queryWrapper);
    }

    @Override
    public List<Type> queryAllType() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("deleted",0);
        return typeMapper.selectList(queryWrapper);
    }

    @Override
    public void updateById(Type type) {
        typeMapper.updateById(type);
    }

    @Override
    public boolean deleteById(Integer id) {
        Type t = queryById(id);
        if(null==t){
            return false;
        }else{
            Type type = new Type();
            type.setDeleted(1);
            type.setTypeId(id);
            typeMapper.updateById(type);
            return true;
        }
    }

    @Override
    public Type queryById(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("type_id",id);
        queryWrapper.eq("deleted",0);
        return  typeMapper.selectOne(queryWrapper);
    }

    @Override
    public int updateTypeStart(Integer id) {
        int result = 0;

        Type type = queryById(id);

        if(null==type){

        }else{
            type.setEnable(0);
            typeMapper.updateById(type);
            result = 1;
        }
        return result;
    }

    @Override
    public int updateTypeBlock(Integer id) {
        int result = 0;

        Type type = queryById(id);

        if(null==type){

        }else{
            type.setEnable(1);
            typeMapper.updateById(type);
            result = 1;
        }
        return result;
    }
}
