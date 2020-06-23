package com.lxq.blog.module.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxq.blog.module.mapper.BlogMapper;
import com.lxq.blog.module.pojo.Blog;
import com.lxq.blog.module.pojo.Type;
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
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Blog blog) {
        //调用查询findBlogById方法
        Blog blogEntity = findBlogById(blog.getBlogId());
        if (null==blogEntity){
            blog.setBlogId(idWorker.nextId()+"");
            //调用新增方法
            blogMapper.insert(blog);
            //联级操作 新增博客 分类数量加1
            //获取分类编号
            Integer blogType = blog.getBlogType();
            //查询数据
            Type type = typeService.queryById(blogType);
            //在原有基础上+1
            type.setTypeBlogCount(type.getTypeBlogCount()+1);
            //进行修改
            typeService.saveOrUpdate(type);
        }else {
            //乐观锁
            blog.setVersion(blog.getVersion()+1);
            //实例化创建QueryWrapper对象
            QueryWrapper wrapper = new QueryWrapper();
            //修改条件
            wrapper.eq("blog_id",blogEntity.getBlogId());
            //修改条件
            wrapper.eq("version",blogEntity.getVersion());
            //调用通过id修改方法
            blogMapper.update(blog,wrapper);
            //联级操作 新增博客 分类数量加1
            //是否修改了分类
            if (!blogEntity.getBlogType().equals(blog.getBlogType())){
                //获取分类编号
                Integer typeId = blog.getBlogType();
                //查询数据
                Type type = typeService.queryById(typeId);
                //在原有基础上+1
                type.setTypeBlogCount(type.getTypeBlogCount()+1);
                //进行修改
                typeService.saveOrUpdate(type);
                //同时 另外一个需要减 1
                Integer typeId2 = blogEntity.getBlogType();
                //查询数据
                Type type2 = typeService.queryById(typeId2);
                //如果等于数量0    不进行操作
                if(type2.getTypeBlogCount()!=0){
                    //-1
                    type.setTypeBlogCount(type2.getTypeBlogCount()-1);
                    //修改方法
                    typeService.saveOrUpdate(type2);
                }
            }

        }
    }

    @Override
    public Blog findBlogById(String id) {
        //查询字段
        String [] column= {"blog_id","blog_title","blog_image","blog_content",
                "blog_goods","blog_read","blog_collection","blog_type","blog_remark","blog_comment","version"};
        //实例化创建QueryWrapper对象
        QueryWrapper wrapper = new QueryWrapper();
        //查询列
        wrapper.select(column);
        //查询条件
        wrapper.eq("blog_id",id);
        //查询条件
        wrapper.eq("deleted",0);
        return blogMapper.selectOne(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BlogVo readById(String id) {
        //通过编号查询方法
        Blog blog =  findBlogById(id);
        //阅读数加1
        blog.setBlogRead(blog.getBlogRead()+1);
        //调用修改方法
        saveOrUpdate(blog);
        //分类和博客vo
        BlogVo blogVo = new BlogVo();
        //复制 方法
        BeanUtils.copyProperties(blog,blogVo);
        //赋值
        blogVo.setType(typeService.queryById(blog.getBlogType()));
        return blogVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBlog(String id) {
        //查询数据
        Blog blog = findBlogById(id);
        //逻辑删除 赋值
        blog.setDeleted(1);
        //修改方法
        blogMapper.updateById(blog);
        //删除同事帖子分类数-1
        Type type = typeService.queryById(blog.getBlogType());
        //查询
        type.setTypeBlogCount(type.getTypeBlogCount()-1);
        //修改
        typeService.saveOrUpdate(type);
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
