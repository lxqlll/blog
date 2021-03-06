package com.lxq.blog.module.controller;

import com.lxq.blog.enums.ResultEnum;
import com.lxq.blog.exception.MyException;
import com.lxq.blog.module.pojo.User;
import com.lxq.blog.module.service.UserService;
import com.lxq.blog.utils.IdWorker;
import com.lxq.blog.utils.Page;
import com.lxq.blog.utils.Result;
import com.lxq.blog.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
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
 * @remark 用户控制层
 * @author lxq
 * @createTime 2020年5月11日15:51:50
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 声明UserService对象
     */
    @Autowired
    private UserService userService;

    /**
     * 新增或者修改用户
     * @param user 音乐类型实体
     * @return Result 统一返回类型
     */
    @PostMapping(value = "/saveOrUpdate")
    public Result<User> saveOrUpdate(@RequestBody User user){
        //判断参数是否为空
        if(null==user)return new Result<>(ResultEnum.PARAMS_NULL);
        //判断有无数据
        if (userService.saveOrUpdate(user)) {
            return new Result(ResultEnum.SUCCESS);
        }else {
            return new Result(ResultEnum.ERROR);
        }
    }

    /**
     * 根据id查询用户
     * @param id 编号
     * @return Result 统一返回类型
     */
    @GetMapping(value = "/list/{id}")
    public Result<User> getMusicById(@PathVariable Integer id){
        //判断参数是否为空
        if(null==id)return new Result<>(ResultEnum.PARAMS_NULL);
        return new Result(userService.queryById(id));
    }


    /**
     * 删除用户类型
     * @param id 编号
     * @return Result 统一返回类型
     */
    @DeleteMapping(value = "/deleteById/{id}")
    public Result<User> deleteById(@PathVariable Integer id){
        //判断参数是否为空
        if (null==id)return new Result(ResultEnum.PARAMS_NULL);
        //删除方法
        if (userService.deleteById(id)) {
            return new Result(ResultEnum.SUCCESS.getCode(),"删除成功");
        }else {
            return new Result(ResultEnum.ERROR.getCode(),"删除失败");
        }
    }


    /**
     * 分页查询
     * @param page 分页对象
     * @return Result 统一返回类型
     */
    @PostMapping("/selectByPage")
    public Result selectByPage(@RequestBody Page page){
        //获取排序列
        String sortColumn =  page.getSortColumn();
        //判断是否为空
        if(StringUtils.isNotBlank(sortColumn)){
            //创建数组
                String[] sortColumns = {"sex", "created_time", "update_time"};
            //将数组转化为集合
            List<String> sortList= Arrays.asList(sortColumns);
            /**
             * contains方法是否包含字符
             * toLowerCase转化为小写
             */
            if (!sortList.contains(sortColumn.toLowerCase())){
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "排序参数不合法！");
            }
        }
        page = userService.selectPage(page);
        return new Result(page);
    }

    /**
     * 获取用户名
     * @return Result 统一返回类型
     */
    @GetMapping(value = "/getUserName")
    public Result<User> getUserName(){
        //雪花算法
        IdWorker idWorker = new IdWorker();
        //注册 随机id                =================               不使用了
        String id = idWorker.nextId()+"";
        //id统一长度为后6位
        String id2 = id.substring(id.length()-6,id.length());
        return new Result("随机用户名",id2);
    }

    /**
     * 获取用户名
     * @return Result 统一返回类型
     */
    @PutMapping(value = "/passwordReset")
    public Result<User> passwordReset(@RequestBody List<Integer> userIds){
        //重置密码
        userService.updateBatchById(userIds);
        return new Result("重置密码成功");
    }

    /**
     * 注册
     * @param user
     * @return
     * @throws MyException
     */
    @PostMapping(value="/register")
    public Result register(@RequestBody User user) throws MyException {
        userService.insert(user);
        return new Result("注册成功");
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping(value = "/login")
    public Result login(@RequestBody User user){
        //获取用户名
        String userName = user.getUsername();
        //获取密码
        String password = user.getPassword();
        //参数验证
        if (user==null || StringUtils.isBlank(userName) || StringUtils.isBlank(password))
            return new Result(ResultEnum.PARAMS_NULL.getCode(),"参数为空");
        //获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        //实例化创建AuthenticationToken对象
        AuthenticationToken authenticationToken = new UsernamePasswordToken(userName,password);
        try {
            //执行登录方法
            subject.login(authenticationToken);
        } catch (AuthenticationException e) {
            return new Result(ResultEnum.ERROR.getCode(),"用户名或者密码错误");
        }
        //获取唯一标识
        Serializable serializable = subject.getSession().getId();
        User User = (com.lxq.blog.module.pojo.User) subject.getPrincipal();
        User.setPassword("");
        User.setDeleted(null);
        //实例化创建map集合
        Map<String,Object> resultMap = new HashMap<>(2);
        //添加数据
        resultMap.put("token",serializable);
        resultMap.put("user",User);
        return new Result(resultMap);
    }

}
