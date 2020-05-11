package com.lxq.blog.module.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxq.blog.exception.MyException;
import com.lxq.blog.module.mapper.AboutMapper;
import com.lxq.blog.module.pojo.About;
import com.lxq.blog.module.service.AboutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void saveOrUpdate(About about) throws MyException {
        About a = getAboutById(about.getAboutId()); //调用根据编号查询方法
        if(null==a){ //判断有无数据
            try {
                aboutMapper.insert(about);
            } catch (Exception e) {
                throw new MyException("新增闲言失败呢！");
            }
        }else {
            about.setVersion(about.getVersion()+1);
            QueryWrapper queryWrapper = new QueryWrapper();//实例化创建QueryWrapper对象
            queryWrapper.eq("version",about.getVersion()); //添加修改条件
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
        queryWrapper.eq("enable",0);//添加条件 是否启用
        queryWrapper.select(getColumn); //获取查询的列
        About about = aboutMapper.selectOne(queryWrapper); //调用查询方法
       return about;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public About readingAbout(Integer id) {
        About about = getAboutById(id);//调用查询编号的方法
        about.setAboutRead(about.getAboutRead()+1); //修改阅读数
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
        About about = getAboutById(id);
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
    public void start(Integer id)  {
        updateOne(id,0);
    }

    @Override
    public void disable(Integer id)  {
        updateOne(id,1);
    }

    @Override
    public int updateOne(Integer id,Integer state){
        int result = 0;
        About about = getAboutById(id);
        About a = new About();
        a.setEnable(state);
        a.setAboutId(about.getAboutId());
        a.setVersion(about.getVersion());
        if(a.getAboutId()!=null){
            aboutMapper.updateById(a);
            result=1;
        }
        return result;
    }

}
