package com.atguigu.guli.service.oss.controller;

import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.common.base.result.ResultCodeEnum;
import com.atguigu.guli.common.base.util.ExceptionUtils;
import com.atguigu.guli.service.base.exception.GuliException;
import com.atguigu.guli.service.oss.service.FeilService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/admin/oss/file")
@CrossOrigin
@Api(description = "阿里云文件管理")
@Slf4j
public class FileController {

    @Autowired
    private FeilService feilService;


    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public R upload(
            @ApiParam(name = "file",value = "文件",required = true)
            @RequestParam("file") MultipartFile file,
            @ApiParam(name = "module",value = "模块",required = true)
        @RequestParam("module") String module){

        try {
            String originalFilename = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();

            String uploadURL = feilService.upload(inputStream, module, originalFilename);

            return R.ok().message("文件上传成功").data("url",uploadURL);
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
    }

    @ApiOperation(value = "文件删除")
    @DeleteMapping("remove")
    public R removeFile(
            @ApiParam(name = "url",value = "要删除的文件的url地址",required = true)
            @RequestBody String url
    ){
        try{
            feilService.removeFile(url);
            return R.ok().message("删除成功");
        }catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.FILE_DELETE_ERROR);
        }

    }


}
