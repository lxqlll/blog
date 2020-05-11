package com.lxq.blog.module.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxq.blog.module.mapper.BlogMapper;
import com.lxq.blog.module.pojo.Blog;
import com.lxq.blog.module.service.BlogService;
import com.lxq.blog.module.service.TypeService;
import com.lxq.blog.utils.IdWorker;
import com.lxq.blog.utils.Page;
import com.lxq.blog.vo.BlogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 * 博客表服务实现类
 * </p>
 *
 * @author lxq
 * @date 2020年5月9日14:42:56
 * @Version 1.0
 */
@Service
@Slf4j
public class BlogServiceImpl implements BlogService {

    /**
     * 声明BlogMapper对象
     */
    @Autowired
    private BlogMapper blogMapper;
    /**
     * 声明TypeMapper对象
     */
    @Autowired
    private TypeService typeService;
    /**
     * 声明IdWorker对象
     */
    @Autowired
    private IdWorker idWorker;


    @Override
    public void saveOrUpdate(Blog blog) {
        Blog b = findBlogById(blog.getBlogId()); //调用查询findBlogById方法
        System.out.println(b.toString());
        if (null==b){
            blog.setBlogId(idWorker.nextId()+"");
            blogMapper.insert(blog); //调用新增方法
        }else {
            blog.setVersion(blog.getVersion()+1); //乐观锁
            QueryWrapper wrapper = new QueryWrapper();//实例化创建QueryWrapper对象
            wrapper.eq("blog_id",b.getBlogId());   //修改条件
            wrapper.eq("version",b.getVersion());  //修改条件
            blogMapper.update(blog,wrapper); //调用通过id修改方法
        }
    }

    @Override
    public Blog findBlogById(String id) {
        String [] column= {"blog_id","blog_title","blog_image","blog_content",
                "blog_goods","blog_read","blog_collection","blog_type","blog_remark","blog_comment","version"}; //查询字段
        QueryWrapper wrapper = new QueryWrapper();//实例化创建QueryWrapper对象
        wrapper.select(column);   //查询列
        wrapper.eq("blog_id",id);   //查询条件
        wrapper.eq("deleted",0);   //查询条件
        return blogMapper.selectOne(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BlogVo readById(String id) {
        Blog blog =  findBlogById(id);  //通过编号查询方法
        blog.setBlogRead(blog.getBlogRead()+1); //阅读数加1
        saveOrUpdate(blog); //调用修改方法
        BlogVo blogVo = new BlogVo();
        BeanUtils.copyProperties(blog,blogVo);
        blogVo.setType(typeService.queryById(blog.getBlogType()));
        return blogVo;
    }

    @Override
    public void deleteBlog(String id) {
        Blog blog = findBlogById(id);
        blog.setDeleted(1);
        blogMapper.updateById(blog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<BlogVo> getByPage(Page<BlogVo> page){
        //查询所有数据
        List<BlogVo> blogVoList = blogMapper.getByPage(page);
        //将数据放入page的集合中
        page.setList(blogVoList);
        //查询数据记录
        int count = blogMapper.getCountByPage(page);
        //将数据放入page中
        page.setTotalCount(count);
        return page;
    }
}
