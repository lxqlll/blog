package com.lxq.blog;

import com.lxq.blog.module.mapper.UserMapper;
import com.lxq.blog.utils.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        Page page= new Page<>();
        userMapper.getByPage(page);
    }

}
