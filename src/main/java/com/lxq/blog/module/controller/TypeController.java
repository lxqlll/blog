package com.lxq.blog.module.controller;

import com.lxq.blog.enums.ResultEnum;
import com.lxq.blog.module.pojo.Type;
import com.lxq.blog.module.service.TypeService;
import com.lxq.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @PostMapping(value = "/saveOrUpdate")
    public Result<Type> saveOrUpdate(@RequestBody Type type){
        if(null==type && type.getTypeName().equals(""))return new Result<>(ResultEnum.PARAMS_NULL);

        if (typeService.saveOrUpdate(type)) {
            return new Result(ResultEnum.SUCCESS);
        }else {
            return new Result(ResultEnum.ERROR);
        }
    }

    @GetMapping(value = "/list/{id}")
    public Result<Type> getTypeById(@PathVariable Integer id){
        if(null==id)return new Result<>(ResultEnum.PARAMS_NULL);
        return new Result(typeService.queryById(id));
    }

    @GetMapping(value = "/getType")
    public Result<Type> getType(){
        return new Result(typeService.showAllType());
    }

    @GetMapping(value = "/list")
    public Result<Type> list(){
        return new Result(typeService.queryAllType());
    }

    @DeleteMapping(value = "/deleteById/{id}")
    public Result<Type> deleteById(@PathVariable Integer id){
        if (null==id)return new Result(ResultEnum.PARAMS_NULL);
        if (typeService.deleteById(id)) {
            return new Result(ResultEnum.SUCCESS.getCode(),"删除成功");
        }else {
            return new Result(ResultEnum.ERROR.getCode(),"删除失败");
        }
    }




}
