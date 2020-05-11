package com.lxq.blog.module.controller;

import com.lxq.blog.enums.ResultEnum;
import com.lxq.blog.module.pojo.Blog;
import com.lxq.blog.module.service.BlogService;
import com.lxq.blog.utils.Page;
import com.lxq.blog.utils.Result;
import com.lxq.blog.utils.StringUtils;
import com.lxq.blog.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @remark 博客控制层
 * @author lxq
 * @createTime 2020年5月9日09:10:57
 * @version 1.0
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    /**
     * 声明BlogService对象
     */
    @Autowired
    private BlogService blogService;

    /**
     * 新增或者修改方法
     * @param blog 博客表实体
     * @return Result 统一返回类型
     */
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Blog blog){
        if (blog==null)return new Result(ResultEnum.PARAMS_NULL);   //判断有无数据
        blogService.saveOrUpdate(blog);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据编号查询
     * @return Result 统一返回类型
     */
    @GetMapping("/getBlogById/{blogId}")
    public Result getBlogById(@PathVariable String blogId){
        if(blogId==null)return new Result(ResultEnum.PARAMS_NULL); //判断参数是否为空
        return new Result(blogService.findBlogById(blogId));
    }

    /**
     * 根据阅读量查询
     * @param id 编号
     * @return Result 统一返回类型
     */
    @GetMapping("read/{id}")
    public Result read(@PathVariable String id){
        if(id==null)return new Result(ResultEnum.PARAMS_NULL); //判断参数是否为空
        BlogVo blogVo = blogService.readById(id);   //调用方法
        return new Result(blogVo);
    }

    /**
     * 删除博客方法
     * @param id 编号
     * @return Result 统一返回类型
     */
    @DeleteMapping(value = "/deleteBlog/{id}")
    public Result deleteBlog(@PathVariable String id){
        if(id==null)return new Result(ResultEnum.PARAMS_NULL); //判断参数是否为空
        blogService.deleteBlog(id); //调用删除方法
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 分页查询
     * @return
     */
    @GetMapping("/pageBlog")
    public Result pageBlog(@RequestBody Page page){
        String sortColumn =  page.getSortColumn();//获取排序列
        if(StringUtils.isNotBlank(sortColumn)){ //判断是否为空
            String [] sortColumns = {"blog_goods", "blog_read", "blog_collection",
                    "type_name", "blog_comment", "created_time", "update_time"};    //创建数组
            List<String>  sortList=Arrays.asList(sortColumns); //将数组转化为集合
            /**
             * contains方法是否包含字符
             * toLowerCase转化为小写
             */
            if (!sortList.contains(sortColumn.toLowerCase())){
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "排序参数不合法！");
            }
        }
        page = blogService.getByPage(page);
        return new Result(page);
    }

}
