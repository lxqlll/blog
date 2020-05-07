package com.lxq.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @remark springboot启动类
 * @author lxq
 * @createTime 2020年5月7日00点55分
 * @version 1.0
 */
@SpringBootApplication
@MapperScan(value = "com.lxq.blog.module.mapper")
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
