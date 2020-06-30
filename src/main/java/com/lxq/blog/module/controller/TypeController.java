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
        //判断参数是否为空
        if(null==type)return new Result<>(ResultEnum.PARAMS_NULL);
        //判断有无数据
        if (typeService.saveOrUpdate(type)) {
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
    @GetMapping(value = "/getTypeById/{id}")
    public Result<Type> getTypeById(@PathVariable Integer id){
        //判断参数是否为空
        if(null==id)return new Result<>(ResultEnum.PARAMS_NULL);
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
     * 前端显示数量
     * @return Result 统一返回类型
     */
    @GetMapping(value = "/getTypeCount")
    public Result<Type> getTypeCount(){
        return new Result(typeService.showAllTypeCout());
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
        //判断参数是否为空
        if (null==id)return new Result(ResultEnum.PARAMS_NULL);
        //删除方法
        if (typeService.deleteById(id)) {
            return new Result(ResultEnum.SUCCESS.getCode(),"删除成功");
        }else {
            return new Result(ResultEnum.ERROR.getCode(),"删除失败");
        }
    }

    /**
     * 启用分类
     * @param id 编号
     * @return Result 统一返回类型
     */
    @PutMapping(value = "/stater/{id}")
    public Result staterTypeById(@PathVariable Integer id){
        //实例化创建Type对象
        Type type = new Type();
        //赋值
        type.setTypeId(id);
        type.setEnable(1);
        //调用修改方法
        typeService.updateById(type);
        return new Result(ResultEnum.SUCCESS.getCode(),"启用成功");
    }





    /**
     * 禁用方法
     * @param id 编号
     * @return Result 统一返回类型
     */
    @PutMapping(value = "/disabled/{id}")
    public Result disabledTypeById(@PathVariable Integer id){
        //实例化创建Type对象
        Type type = new Type();
        //赋值
        type.setTypeId(id);
        type.setEnable(0);
        //调用修改方法
        typeService.updateById(type);
        return new Result(ResultEnum.SUCCESS.getCode(),"禁用成功");
    }


}
