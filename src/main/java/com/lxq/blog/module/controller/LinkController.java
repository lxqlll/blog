package com.lxq.blog.module.controller;

import com.lxq.blog.enums.ResultEnum;
import com.lxq.blog.exception.MyException;
import com.lxq.blog.module.pojo.Link;
import com.lxq.blog.module.service.LinkService;
import com.lxq.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @remark 友情链接控制器
 * @author lxq
 * @createTime 2020年5月9日09:10:57
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/link")
public class LinkController {

    /**
     * 声明LinkService对象
     */
    @Autowired
    private LinkService linkService;

    /**
     * 新增或者修改友情链接方法
     * @param link 友情链接实体
     * @return Result 统一返回类型
     * @throws MyException 自定义异常
     */
    @PostMapping(value = "/saveOrUpdate")
    public Result saveOrUpdateLink(@RequestBody Link link) throws MyException {
        //判断参数是否为空
        if (null==link)return new Result(ResultEnum.PARAMS_NULL);
        try {
            //调用修改或者删除方法
            linkService.saveOrUpdate(link);
        } catch (Exception e) {
            throw new MyException("添加或者修改失败");
        }
        return new Result(ResultEnum.SUCCESS.getCode(),"添加或者修改成功");
    }

    /**
     * 删除友情链接方法
     * @param id 友情链接编号
     * @return Result 统一返回类型
     */
    @DeleteMapping(value = "/deleteLink/{id}")
    public Result deleteLink(@PathVariable Integer id) throws MyException {
        //判断有无参数
        if (null==id)return new Result(ResultEnum.PARAMS_NULL);
        //调用删除方法
        linkService.deleteLink(id);
        return new Result(ResultEnum.SUCCESS.getCode(),"删除成功");
    }

    /**
     * 根据id查询友情链接
     * @param id 友情链接编号
     * @return Result 统一返回类型
     */
    @GetMapping(value = "/getLinkById/{id}")
    public Result getLinkById(@PathVariable Integer id){
        //判断参数是否为空
        if (null==id)return new Result(ResultEnum.PARAMS_NULL);
        return new Result(linkService.getLinkById(id));
    }

    /**
     * 查询所有友情链接
     * @return Result 统一返回类型
     */
    @GetMapping(value = "/getLink")
    public Result getLink(){
        return new Result(linkService.getLink());
    }

}
