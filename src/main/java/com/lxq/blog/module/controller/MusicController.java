package com.lxq.blog.module.controller;


import com.lxq.blog.enums.ResultEnum;
import com.lxq.blog.module.pojo.Music;
import com.lxq.blog.module.service.MusicService;
import com.lxq.blog.utils.Page;
import com.lxq.blog.utils.Result;
import com.lxq.blog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @remark 音乐控制器
 * @author lxq
 * @createTime 2020年5月7日11:51:07
 * @version 1.0
 */
@RestController
@RequestMapping("/music")
public class MusicController {

    /**
     * 声明MusicService对象
     */
    @Autowired
    private MusicService musicService;


    /**
     * 新增或者修改音乐
     * @param music 音乐类型实体
     * @return Result 统一返回类型
     */
    @PostMapping(value = "/saveOrUpdate")
    public Result<Music> saveOrUpdate(@RequestBody Music music){
        //判断参数是否为空
        if(null==music)return new Result<>(ResultEnum.PARAMS_NULL);
        //判断有无数据
        if (musicService.saveOrUpdate(music)) {
            return new Result(ResultEnum.SUCCESS);
        }else {
            return new Result(ResultEnum.ERROR);
        }
    }

    /**
     * 根据id查询帖子音乐
     * @param id 编号
     * @return Result 统一返回类型
     */
    @GetMapping(value = "/list/{id}")
    public Result<Music> getMusicById(@PathVariable Integer id){
        //判断参数是否为空
        if(null==id)return new Result<>(ResultEnum.PARAMS_NULL);
        return new Result(musicService.queryById(id));
    }


    /**
     * 删除音乐类型
     * @param id 编号
     * @return Result 统一返回类型
     */
    @DeleteMapping(value = "/deleteById/{id}")
    public Result<Music> deleteById(@PathVariable Integer id){
        //判断参数是否为空
        if (null==id)return new Result(ResultEnum.PARAMS_NULL);
        //删除方法
        if (musicService.deleteById(id)) {
            return new Result(ResultEnum.SUCCESS.getCode(),"删除成功");
        }else {
            return new Result(ResultEnum.ERROR.getCode(),"删除失败");
        }
    }

    /**
     * 根据编号启用方法
     * @param id 编号
     * @return Result 统一返回类型
     */
    @PutMapping("/start/{id}")
    public Result start(@PathVariable Integer id){
        //判断参数是否为空
        if (null==id)return new Result(ResultEnum.PARAMS_NULL);
        //调用阅读方法
        musicService.start(id);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据编号禁用方法
     * @param id 编号
     * @return Result 统一返回类型
     */
    @PutMapping("/forbidden/{id}")
    public Result forbidden(@PathVariable Integer id){
        //判断参数是否为空
        if (null==id)return new Result(ResultEnum.PARAMS_NULL);
        //调用阅读方法
        musicService.forbidden(id);
        return new Result(ResultEnum.SUCCESS);
    }


    /**
     * 前端显示功能
     * @return Result 统一返回类型
     */
    @GetMapping("/list")
    public Result list(){
        return new Result(musicService.showList());
    }


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
            String[] sortColumns = {"artist", "created_time", "enabled"};
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
        //分页方法
        page = musicService.getList(page);
        return new Result(page);
    }
}
