package com.lxq.blog.module.controller;

import com.lxq.blog.enums.ResultEnum;
import com.lxq.blog.module.pojo.Type;
import com.lxq.blog.module.service.TypeService;
import com.lxq.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @remark 帖子类型控制器
 * @author lxq
 * @createTime 2020年5月9日09:10:57
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/type")
public class TypeController {
    /**
     * 声明TypeService对象
     */
    @Autowired
    private TypeService typeService;

    /**
     * 根据帖子名来判断新增或者修改
     * @param type 帖子类型实体
     * @return Result 统一返回类型
     */
    @PostMapping(value = "/saveOrUpdate")
    public Result<Type> saveOrUpdate(@RequestBody Type type){
        if(null==type && type.getTypeName().equals(""))return new Result<>(ResultEnum.PARAMS_NULL);//判断参数是否为空
        if (typeService.saveOrUpdate(type)) {   //判断有无数据
            return new Result(ResultEnum.SUCCESS);
        }else {
            return new Result(ResultEnum.ERROR);
        }
    }

    /**
     * 根据id查询帖子类型
     * @param id 编号
     * @return Result 统一返回类型
     */
    @GetMapping(value = "/list/{id}")
    public Result<Type> getTypeById(@PathVariable Integer id){
        if(null==id)return new Result<>(ResultEnum.PARAMS_NULL);    //判断参数是否为空
        return new Result(typeService.queryById(id));
    }

    /**
     * 前端显示所有数据
     * @return Result 统一返回类型
     */
    @GetMapping(value = "/getType")
    public Result<Type> getType(){
        return new Result(typeService.showAllType());
    }

    /**
     * 后端显示所有数据
     * @return Result 统一返回类型
     */
    @GetMapping(value = "/list")
    public Result<Type> list(){
        return new Result(typeService.queryAllType());
    }

    /**
     * 删除帖子类型
     * @param id 编号
     * @return Result 统一返回类型
     */
    @DeleteMapping(value = "/deleteById/{id}")
    public Result<Type> deleteById(@PathVariable Integer id){
        if (null==id)return new Result(ResultEnum.PARAMS_NULL); //判断参数是否为空
        if (typeService.deleteById(id)) { //删除方法
            return new Result(ResultEnum.SUCCESS.getCode(),"删除成功");
        }else {
            return new Result(ResultEnum.ERROR.getCode(),"删除失败");
        }
    }




}
