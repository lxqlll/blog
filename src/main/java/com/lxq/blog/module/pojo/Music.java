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
    /**
     * 编号
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 歌曲名
     */
    private String name;
    /**
     * 歌手
     */
    private String artist;
    /**
     * 歌曲链接
     */
    private String url;
    /**
     * 封面
     */
    private String cover;
    /**
     * 歌词
     */
    private String lrc;
    /**
     * 创建时间
     */
    private String createdTime;
    /**
     * 是否启用
     */
    @TableField("enabled")
    private Integer enabled;
    /**
     * 是否删除
     */
    private Integer deleted;

}
