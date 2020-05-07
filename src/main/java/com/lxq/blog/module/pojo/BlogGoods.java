package com.lxq.blog.module.pojo;

import lombok.Data;


/**
 * 点赞实体
 * @Author: lxq
 * @Date: 2020/2/16 10:22
 * @Version 1.0
 */
@Data
public class BlogGoods {

    private String id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 博客id
     */
    private String blogId;

}
