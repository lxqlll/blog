package com.lxq.blog.module.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 帖子类型表实体类
 * </p>
 *
 * @author lxq
 * @date 2020年5月8日13:23:38
 * @Version 1.0
 */
@Data
@TableName(value = "bl_type")
public class Type implements Serializable {

    private static final long serialVersionUID = -952315810554536348L;

    /**
     * 分类id
     */
    @TableId(value = "type_id",type = IdType.AUTO)
    private Integer typeId;

    /**
     * 分类名称
     */
    @TableField(value = "type_name")
    private String typeName;

    /**
     * 帖子数
     */
    private Integer typeBlogCount;

    /**
     * 是否启用，0否1是
     */
    private Integer enable;

    /**
     * 是否删除，0否1是
     */
    private Integer deleted;

}
