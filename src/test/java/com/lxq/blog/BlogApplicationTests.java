package com.lxq.blog;

import com.lxq.blog.module.mapper.AboutMapper;
import com.lxq.blog.utils.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    AboutMapper aboutMapper;

    @Test
    void contextLoads() {
        Page page= new Page<>();
        aboutMapper.getByPage(page);
    }

}
