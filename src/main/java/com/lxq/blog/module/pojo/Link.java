package com.lxq.blog.module.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 友情链接
 * @Author: lxq
 * @Date: 2020/2/9 14:43
 * @Version 1.0
 */
@Data
@TableName(value = "bl_link")
public class Link {
    @TableId(value = "link_id",type = IdType.AUTO)
    private Integer linkId;
    private String linkName;
    private String linkUrl;
    private String createdTime;
    private String updateTime;
    private Integer version;
    private Integer deleted;
}
