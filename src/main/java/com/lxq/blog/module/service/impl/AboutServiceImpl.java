package com.lxq.blog.module.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxq.blog.exception.MyException;
import com.lxq.blog.module.mapper.AboutMapper;
import com.lxq.blog.module.pojo.About;
import com.lxq.blog.module.service.AboutService;
import com.lxq.blog.utils.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  闲言服务层
 * </p>
 *
 * @author lxq
 * @date 2020年5月11日15:57:01
 * @Version 1.0
 */
@Service
@Slf4j
public class AboutServiceImpl implements AboutService {

    /**
     * 声明AboutMapper对象
     */
    @Autowired
    private AboutMapper aboutMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(About about) throws MyException {
        System.out.println("[null]==>"+about.getAboutId());
        About a = this.getAboutById(about.getAboutId()); //调用根据编号查询方法
        System.out.println(a);
        if(null==a){ //判断有无数据
            try {
                aboutMapper.insert(about);
            } catch (Exception e) {
                throw new MyException("新增闲言失败呢！");
            }
        }else {
            Integer version = about.getVersion();
            about.setVersion(about.getVersion()+1);
            QueryWrapper queryWrapper = new QueryWrapper();//实例化创建QueryWrapper对象
            queryWrapper.eq("version",version); //添加修改条件
            queryWrapper.eq("about_id",about.getAboutId());
            try {
                aboutMapper.update(about,queryWrapper);
            } catch (Exception e) {
                throw new MyException("修改闲言失败呢！");
            }
        }
    }

    @Override
    public About getAboutById(Integer id) {
        String [] getColumn = {"about_id","about_title", "about_content", "about_read","version","deleted"}; //查询列
        QueryWrapper queryWrapper = new QueryWrapper();//实例化创建QueryWrapper对象
        queryWrapper.eq("deleted",0);//添加条件 是否被删除
        queryWrapper.eq("about_id",id);//添加条件 编号
        queryWrapper.eq("enable",0);//添加条件 是否启用
        queryWrapper.select(getColumn); //获取查询的列
        About about = aboutMapper.selectOne(queryWrapper); //调用查询方法
       return about;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public About readingAbout(Integer id) {
        About about = this.getAboutById(id);//调用查询编号的方法
        about.setAboutRead(about.getAboutRead()+1); //修改阅读数
        try {
            if(null!=about) saveOrUpdate(about); //调用修改方法
        } catch (MyException myException) {
            new MyException("阅读失败!");
        }
        return about;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAbout(Integer id) {
        About about = this.getAboutById(id);
        About a = new About();
        a.setDeleted(1);
        a.setAboutId(about.getAboutId());
        a.setVersion(about.getVersion());
        try {
            if(null!=about) saveOrUpdate(a); //调用修改方法
        } catch (MyException myException) {
            new MyException("删除失败!");
        }
    }

    @Override
    @Transactional
    public void start(Integer id)  {
        About about = aboutMapper.selectById(id);
        if (null!=about) {
            about.setEnable(0);
            aboutMapper.updateById(about);
        }
    }

    @Override
    @Transactional
    public void disable(Integer id)  {
        About about = aboutMapper.selectById(id);
        if (null!=about) {
            about.setEnable(1);
            aboutMapper.updateById(about);
        }
    }



    @Override
    public Page<About> getByPage(Page page) {
        //查询所有数据
        List<About> blogVoList = aboutMapper.getByPage(page);
        //将数据放入page的集合中
        page.setList(blogVoList);
        //查询数据记录
        int count = aboutMapper.getCountByPage(page);
        //将数据放入page中
        page.setTotalCount(count);
        return page;
    }


}
