package com.lxq.blog.module.controller;

import com.lxq.blog.enums.ResultEnum;
import com.lxq.blog.module.service.LogService;
import com.lxq.blog.utils.Page;
import com.lxq.blog.utils.Result;
import com.lxq.blog.utils.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
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
     * @param page 分页对象
     * @return Result 统一返回类型
     */
    @PostMapping("/page")
    public Result page(@RequestBody Page page){
        //获取排序列
        String sortColumn =  page.getSortColumn();
        //判断是否为空
        if(StringUtils.isNotBlank(sortColumn)){
            //创建数组
            String[] sortColumns = {"log_url", "log_status", "log_method", "log_time", "created_time"};
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
        //分页查询
        page = logService.getByPage(page);
        return new Result(page);
    }

    /**
     * 逻辑删除
     * @param id 编号
     * @return Result 统一返回类型
     */
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id){
        if (null==id)return new Result(ResultEnum.PARAMS_NULL);
        logService.delete(id);
        return new Result(ResultEnum.SUCCESS.getCode(),"删除成功");
    }

    /**
     * 批量删除
     * @param logIdList 集合
     * @return Result 统一返回类型
     */
    @DeleteMapping("/deleteByIds")
    public Result deleteByIds(@RequestBody List<Integer> logIdList){
        if (null==logIdList && logIdList.size()>0)return new Result(ResultEnum.PARAMS_NULL);
        logService.batchDelete(logIdList);
        return new Result(ResultEnum.SUCCESS.getCode(),"删除成功");
    }

    /**
     *全部导出
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response) throws Exception{
        //调用导出方法
        XSSFWorkbook workbook = (XSSFWorkbook) logService.export();
        //设置请求头
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + "日志");
        //刷新缓存
        response.flushBuffer();
        //导出
        workbook.write(response.getOutputStream());
    }
}
