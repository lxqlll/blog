package com.lxq.blog.module.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lxq.blog.excel.annotation.Excel;
import lombok.Data;
import java.io.Serializable;

/**
 * <p>
 * 接口访问日志表实体类
 * </p>
 *
 * @author lxq
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Data
@TableName(value = "bl_log")
public class Log implements Serializable {

    private static final long serialVersionUID = 762815810554536396L;

    /**
     * 日志id
     */
    @Excel(name = "编号")
    @TableId(value = "log_id",type = IdType.AUTO)
    private Integer logId;

    /**
     * 请求路径
     */
    @Excel(name = "请求地址", autoSize = true)
    private String logUrl;

    /**
     * 参数
     */
    private String logParams;

    /**
     * 访问状态，1正常0异常
     */
    @Excel(name = "访问状态，1正常0异常")
    private Integer logStatus;

    /**
     * 异常信息
     */
    @Excel(name = "异常信息")
    private String logMessage;

    /**
     * 请求方式，get、post等
     */
    @Excel(name = "请求方式")
    private String logMethod;

    /**
     * 响应时间
     */
    @Excel(name = "响应时间（毫秒）")
    private Long logTime;

    /**
     * 返回值
     */
    private String logResult;

    /**
     * 请求ip
     */
    @Excel(name = "请求ip", autoSize = true)
    private String logIp;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", autoSize = true)
    private String createdTime;

}
