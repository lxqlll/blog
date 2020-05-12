package com.lxq.blog.module.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: lxq
 * @Date: 2020/2/9 14:45
 * @Version 1.0
 */
@Data
@TableName("bl_music")
public class Music {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String artist;
    private String url;
    private String cover;
    private String lrc;
    private String createdTime;
    @TableField("enable")
    private Integer enabled;
    private Integer deleted;

}
