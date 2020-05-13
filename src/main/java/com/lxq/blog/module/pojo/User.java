package com.lxq.blog.module.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * <p>
 * 用户表实体类
 * </p>
 *
 * @author lxq
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Data
@TableName(value = "bl_user")
public class User implements Serializable {

    private static final long serialVersionUID = -131715810554534737L;

    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别。1男2女
     */
    private Integer sex;

    /**
     * 头像
     */
    private String header;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 创建时间
     */
    private String createdTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 是否删除。0否1是
     */
    private Integer deleted;

}
