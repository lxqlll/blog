package com.lxq.blog.module.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxq.blog.exception.MyException;
import com.lxq.blog.module.mapper.LinkMapper;
import com.lxq.blog.module.pojo.Link;
import com.lxq.blog.module.service.LinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  友情链接服务层
 * </p>
 *
 * @author lxq
 * @date 2020年5月9日08:54:06
 * @Version 1.0
 *
 */
@Service
@Slf4j
public class LinkServiceImpl implements LinkService {

    /**
     * 声明LinkMapper对象
     */
    @Autowired
    private LinkMapper linkMapper;

    @Override
    public void saveOrUpdate(Link link) {
        Integer id = link.getLinkId();
        //查询是否存在数据
        Link linkEntity = getLinkById(id);
        //判断有无数据
        if (null==linkEntity){
            //调用新增友情链接方法
            linkMapper.insert(link);
        }else {
            //乐观锁
            link.setVersion(linkEntity.getVersion()+1);
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("link_id",linkEntity.getLinkId());
            if (link.getCreatedTime()!=null){
                //link.setUpdateTime("");
                link.setUpdateTime(null);
            }
            queryWrapper.eq("version",linkEntity.getVersion());
            //调用修改友情链接方法
            linkMapper.update(link,queryWrapper);
        }
    }

    @Override
    public void deleteLink(Integer id) throws MyException {
        //查询是否存在数据
        Link l = getLinkById(id);
        //判断有无数据
        if (null != l) {
            Link link = new Link();
            link.setLinkId(id);
            link.setVersion(l.getVersion()+1);
            link.setDeleted(1);
            //调用修改友情链接方法
            linkMapper.updateById(link);
        }else {
            throw new MyException("删除失败，数据不存在");
        }
    }

    @Override
    public Link getLinkById(Integer id) {
        //实例化创建QueryWrapper对象
        QueryWrapper queryWrapper = new QueryWrapper();
        //添加条件
        queryWrapper.eq("link_id",id);
        //添加条件
        queryWrapper.eq("deleted",0);
        //调用查询单个方法
        Link link = linkMapper.selectOne(queryWrapper);
        return link;
    }

    @Override
    public List<Link> getLink() {
        //实例化创建QueryWrapper对象
        QueryWrapper queryWrapper = new QueryWrapper();
        //查询字段
        queryWrapper.select("link_id","link_name","link_url","created_time","update_time");
        //添加条件
        queryWrapper.eq("deleted",0);
        //调用查询所有的方法
        List<Link> linkList = linkMapper.selectList(queryWrapper);
        return linkList;
    }
}
