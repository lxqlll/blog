package com.lxq.blog.module.controller;

import com.lxq.blog.enums.ResultEnum;
import com.lxq.blog.utils.Result;
import com.lxq.blog.utils.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件控制层
 * @author lxq
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    /**
     * 声明UploadService对象
     */
    @Autowired
    private UploadService uploadService;

    /**
     * 上传文件
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadImage",method = RequestMethod.POST)
    public Result fileUpload(MultipartFile file){
        String url = uploadService.uploadImage(file);
        return new Result(ResultEnum.SUCCESS.getCode(),"上传文件成功",url);
    }

}
