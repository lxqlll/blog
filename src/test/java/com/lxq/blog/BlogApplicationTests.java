package com.lxq.blog;

import com.lxq.blog.module.controller.AdminController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    AdminController adminController;

    @Test
    void contextLoads() {
        System.out.println(adminController.getAdmin());
    }

}
