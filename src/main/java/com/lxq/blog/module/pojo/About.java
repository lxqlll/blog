package com.lxq.blog.module.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: lxq
 * @Date: 2020/2/9 14:41
 * @Version 1.0
 */
@Data
public class About implements Serializable {

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
