package com.lxq.blog.module.controller;

import com.lxq.blog.enums.ResultEnum;
import com.lxq.blog.exception.MyException;
import com.lxq.blog.module.pojo.About;
import com.lxq.blog.module.service.AboutService;
import com.lxq.blog.utils.Page;
import com.lxq.blog.utils.Result;
import com.lxq.blog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

/**
 * @remark 闲言控制器
 * @author lxq
 * @createTime 2020年5月11日15:51:50
 * @version 1.0
 */
@RequestMapping(value = "/about")
@RestController
public class AboutController {

    /**
     * 声明AboutService对象
     */
    @Autowired
    private AboutService aboutService;

    /**
     * 新增或者修改
     * @param about 闲言实体
     * @return Result 统一返回类型
     */
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody About about)throws MyException {
        if (null==about)return new Result(ResultEnum.PARAMS_ERROR); //判断参数是否为空
        aboutService.saveOrUpdate(about);  //调用新增或者修改方法
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据编号查询
     * @param id 编号
     * @return Result 统一返回类型
     */
    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable Integer id){
        if (null==id)return new Result(ResultEnum.PARAMS_NULL); //判断参数是否为空
        return new Result(aboutService.getAboutById(id));
    }

    /**
     * 根据编号删除
     * @param id 编号
     * @return Result 统一返回类型
     */
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id){
        if (null==id)return new Result(ResultEnum.PARAMS_NULL); //判断参数是否为空
        aboutService.deleteAbout(id); //调用删除方法
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据编号阅读
     * @param id 编号
     * @return Result 统一返回类型
     */
    @GetMapping("/reading/{id}")
    public Result reading(@PathVariable Integer id){
        if (null==id)return new Result(ResultEnum.PARAMS_NULL); //判断参数是否为空
        About about = aboutService.readingAbout(id);//调用阅读方法
        return new Result(about);
    }

    /**
     * 根据编号启用方法
     * @param id 编号
     * @return Result 统一返回类型
     */
    @PutMapping("/start/{id}")
    public Result start(@PathVariable Integer id){
        if (null==id)return new Result(ResultEnum.PARAMS_NULL); //判断参数是否为空
        aboutService.start(id); //调用阅读方法
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据编号禁用方法
     * @param id 编号
     * @return Result 统一返回类型
     */
    @PutMapping("/disable/{id}")
    public Result disable(@PathVariable Integer id){
        if (null==id)return new Result(ResultEnum.PARAMS_NULL); //判断参数是否为空
        aboutService.disable(id); //调用阅读方法
        return new Result(ResultEnum.SUCCESS);
    }


    @PostMapping("/getByPage")
    public Result getByPage(Page page){
        if(page==null)return new Result(ResultEnum.PARAMS_NULL);
        String sortColumn =  page.getSortColumn();//获取排序列
        if(StringUtils.isNotBlank(sortColumn)){ //判断是否为空
            String [] sortColumns = { "about_read", "created_time", "update_time"};    //创建数组
            List<String> sortList= Arrays.asList(sortColumns); //将数组转化为集合
            /**
             * contains方法是否包含字符
             * toLowerCase转化为小写
             */
            if (!sortList.contains(sortColumn.toLowerCase())){
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "排序参数不合法！");
            }
        }
        page = aboutService.getByPage(page);
        return new Result(page);
    }
}
