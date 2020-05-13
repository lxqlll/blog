package com.lxq.blog.module.controller;

import com.lxq.blog.enums.ResultEnum;
import com.lxq.blog.module.service.LogService;
import com.lxq.blog.utils.Page;
import com.lxq.blog.utils.Result;
import com.lxq.blog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @remark 日志控制器
 * @author lxq
 * @createTime 2020年5月11日15:51:50
 * @version 1.0
 */
@RequestMapping(value = "/log")
@RestController
public class LogController {

    /**
     * 声明LogService对象
     */
    @Autowired
    private LogService logService;

    /**
     * 分页查询
     * @return
     */
    @PostMapping("/page")
    public Result page(@RequestBody Page page){
        String sortColumn =  page.getSortColumn();//获取排序列
        if(StringUtils.isNotBlank(sortColumn)){ //判断是否为空
            String[] sortColumns = {"log_url", "log_status", "log_method", "log_time", "created_time"};   //创建数组
            List<String> sortList= Arrays.asList(sortColumns); //将数组转化为集合
            /**
             * contains方法是否包含字符
             * toLowerCase转化为小写
             */
            if (!sortList.contains(sortColumn.toLowerCase())){
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "排序参数不合法！");
            }
        }
        page = logService.getByPage(page);
        return new Result(page);
    }

    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id){
        if (null==id)return new Result(ResultEnum.PARAMS_NULL);
        logService.delete(id);
        return new Result(200,"删除成功");
    }

    @DeleteMapping("/deleteByIds")
    public Result deleteByIds(@RequestBody List<Integer> ids){
        if (null==ids && ids.size()>0)return new Result(ResultEnum.PARAMS_NULL);
        logService.batchDelete(ids);
        return new Result(200,"删除成功");
    }

}
