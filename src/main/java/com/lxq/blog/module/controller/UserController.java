package com.lxq.blog.module.controller;


import com.lxq.blog.enums.ResultEnum;
import com.lxq.blog.enums.StateEnums;
import com.lxq.blog.module.service.UserService;
import com.lxq.blog.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 杨德石
 * @Date: 2020/2/9 20:45
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

}
