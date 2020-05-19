package com.lxq.blog.module.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: lxq
 * @Date: 2020/2/9 14:41
 * @Version 1.0
 */
@Data
@TableName("bl_about")
public class About implements Serializable {
    @TableId(value = "about_id",type = IdType.AUTO)
    private Integer aboutId;
    private String aboutTitle;
    private String aboutContent;
    private Integer aboutRead;
    private String createdTime;
    private String updateTime;
    private Integer version;
    private Integer enable;
    private Integer deleted;
}
