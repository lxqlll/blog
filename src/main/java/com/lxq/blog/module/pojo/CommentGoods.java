package com.lxq.blog.module.pojo;

import lombok.Data;

/**
 * 评论点赞
 * @Author: lxq
 * @Date: 2020/2/16 10:22
 * @Version 1.0
 */
@Data
public class CommentGoods {

    private String id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 评论id
     */
    private String commentId;

}
