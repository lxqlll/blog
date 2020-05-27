package com.lxq.blog.module.controller;

import com.lxq.blog.enums.ResultEnum;
import com.lxq.blog.module.pojo.User;
import com.lxq.blog.module.service.UserService;
import com.lxq.blog.utils.IdWorker;
import com.lxq.blog.utils.Page;
import com.lxq.blog.utils.Result;
import com.lxq.blog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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
        if(null==user)return new Result<>(ResultEnum.PARAMS_NULL);//判断参数是否为空
        if (userService.saveOrUpdate(user)) {   //判断有无数据
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
        if(null==id)return new Result<>(ResultEnum.PARAMS_NULL);    //判断参数是否为空
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
        if (userService.deleteById(id)) { //删除方法
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
        IdWorker idWorker = new IdWorker();
        String id = idWorker.nextId()+"";
        String id2 = id.substring(id.length()-6,id.length());
        return new Result("随机用户名",id2);
    }

    /**
     * 获取用户名
     * @return Result 统一返回类型
     */
    @PutMapping(value = "/passwordReset")
    public Result<User> passwordReset(@RequestBody List<Integer> userIds){
        userService.updateBatchById(userIds);
        return new Result("重置密码成功");
    }




}
