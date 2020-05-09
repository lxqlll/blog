package com.lxq.blog.enums;

import lombok.Getter;

/**
 * @remark 返回结果枚举
 * @author lxq
 * @createTime 2020年5月7日00点55分
 * @version 1.0
 */
@Getter
public enum ResultEnum {
    /**
     * 返回结果枚举，每一个枚举代表一个返回状态
     */
    SUCCESS(200,"操作成功"),
    ERROR(400,"操作失败"),
    DATA_NOT_FOUND(401,"查询失败"),
    PARAMS_NULL(402,"参数不能为空"),
    NOT_LOGIN(403,"当前账号未登录"),
    DELETE_ERROR(404,"删除失败"),
    PARAMS_ERROR(405,"排序参数不合法")
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }


}
