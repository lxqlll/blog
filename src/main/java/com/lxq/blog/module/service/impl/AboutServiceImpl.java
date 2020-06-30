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
        //调用根据编号查询方法
        About aboutEntity = aboutMapper.selectById(about.getAboutId());
        //调用查询是否启用
        List<About> aboutList = this.selectListByEnable();
        //判断有无数据
        if(null==aboutEntity){
            //判断是否存在
            if (aboutList.size()>=1){
                about.setEnable(0);
            }else {
                about.setEnable(1);
            }
            //调用新增方法
            try {
                aboutMapper.insert(about);
            } catch (Exception e) {
                throw new MyException("新增闲言失败呢！");
            }
        }else {
            Integer version = about.getVersion();
            about.setVersion(about.getVersion()+1);
            //实例化创建QueryWrapper对象
            QueryWrapper queryWrapper = new QueryWrapper();
            //添加修改条件
            queryWrapper.eq("version",version);
            queryWrapper.eq("about_id",about.getAboutId());
            try {
                //修改方法
                aboutMapper.update(about,queryWrapper);
            } catch (Exception e) {
                throw new MyException("修改闲言失败呢！");
            }
        }
    }


    @Override
    public About getAboutById(Integer id) {
        //查询列
        String [] getColumn = {"about_id","about_title", "about_content", "about_read","version","deleted"};
        //实例化创建QueryWrapper对象
        QueryWrapper queryWrapper = new QueryWrapper();
        //添加条件 是否被删除
        queryWrapper.eq("deleted",0);
        //添加条件 编号
        queryWrapper.eq("about_id",id);
      //  queryWrapper.eq("enable",0);//添加条件 是否启用
        //获取查询的列
        queryWrapper.select(getColumn);
        //调用查询方法
        About about = aboutMapper.selectOne(queryWrapper);
       return about;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public About readingAbout() {
        QueryWrapper queryWrapper = new QueryWrapper();
        //添加条件 是否被删除
        queryWrapper.eq("deleted",0);
        //添加条件 是否启用
        queryWrapper.eq("enable",1);
        About about = aboutMapper.selectOne(queryWrapper);
        //修改阅读数
        about.setAboutRead(about.getAboutRead()+1);
        try {
            //调用修改方法
            if(null!=about) saveOrUpdate(about);
        } catch (MyException myException) {
            new MyException("阅读失败!");
        }
        return about;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAbout(Integer id) {
        //实例化创建About对象
        About about = this.getAboutById(id);
        //实例化创建About对象
        About aboutEntity = new About();
        //赋值
        aboutEntity.setDeleted(1);
        aboutEntity.setAboutId(about.getAboutId());
        aboutEntity.setVersion(about.getVersion());
        try {
            //调用修改方法
            if(null!=about) saveOrUpdate(aboutEntity);
        } catch (MyException myException) {
            new MyException("删除失败!");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void start(Integer id)  {
        //调用查询方法
        About about = aboutMapper.selectById(id);
        if (null!=about) {
            //启用
            about.setEnable(1);

            aboutMapper.updateById(about);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disable(Integer id)  {
        //调用查询方法
        About about = aboutMapper.selectById(id);
        if (null!=about) {
            //禁用
            about.setEnable(0);
            //id修改
            aboutMapper.updateById(about);
        }
    }



    @Override
    public Page<About> getByPage(Page page) {
        //查询所有数据
        List<About> aboutList = aboutMapper.getByPage(page);
        //将数据放入page的集合中
        page.setList(aboutList);
        //查询数据记录
        int count = aboutMapper.getCountByPage(page);
        //将数据放入page中
        page.setTotalCount(count);
        return page;
    }

    @Override
    public List<About> selectListByEnable() {
        //实例化创建QueryWrapper对象
        QueryWrapper queryWrapper = new QueryWrapper();
        //添加条件
        queryWrapper.eq("enable",1);
        queryWrapper.eq("deleted",0);
        return aboutMapper.selectList(queryWrapper);
    }

}
