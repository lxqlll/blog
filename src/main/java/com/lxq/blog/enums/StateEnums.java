package com.lxq.blog.enums;

import lombok.Getter;

/**
 * @remark 状态码枚举，所有的状态码都在这里不写
 * @author lxq
 * @createTime 2020年5月7日00点55分
 * @version 1.0
 */
@Getter
public enum StateEnums {
    /**
     * 逻辑删除状态
     */
    DELETED(1,"已删除"),
    NOT_DELETED(0,"未删除"),

    /**
     * 启用状态
     */
    ENABLED(1,"启用"),
    NOT_ENABLE(0,"未启用"),

    /**
     * 性别状态
     */
    SEX_MAN(1,"男"),
    SEX_WOMAN(2,"女")
    ;
    private Integer code;
    private String msg;

    StateEnums(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
