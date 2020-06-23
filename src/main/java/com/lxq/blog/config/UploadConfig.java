package com.lxq.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 上传文件配置类
 * @Author: lxq
 * @Date: 2020年5月15日10:20:42
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "upload")
public class UploadConfig {
    //上传路径
    private String baseUrl;
    //文件类型
    private List<String> allowTypes;

}
