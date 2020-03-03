package com.atguigu.guli.service.vod.controller;

import com.aliyuncs.exceptions.ClientException;
import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.common.base.result.ResultCodeEnum;
import com.atguigu.guli.common.base.util.ExceptionUtils;
import com.atguigu.guli.service.base.exception.GuliException;
import com.atguigu.guli.service.vod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@RestController
@RequestMapping("admin/vod/video")
@CrossOrigin
@Api(description = "阿里云视频点播")
@Slf4j
public class VideoController {
    @Autowired
    private VideoService videoService;


    @ApiOperation(value = "阿里云视频上传")
    @PostMapping("upload")
    public R uploadVideo(
            @ApiParam(name = "file",value = "视屏文件",required = true)
            @RequestParam("file")MultipartFile file
            ){
        try {
            String originalFilename = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            String videoId = this.videoService.uploadVideo(inputStream, originalFilename);
            return R.ok().message("视频上传成功").data("videoId",videoId);
        } catch (IOException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.VIDEO_UPLOAD_TOMCAT_ERROR);
        }

    }

    @ApiOperation(value = "阿里云视频删除")
    @DeleteMapping("remove/{videoId}")
    public R removeVideo(
            @ApiParam(name = "videoId",value = "阿里云视频ID",required = true)
            @PathVariable String videoId
    ){
        try {
            this.videoService.removeVideo(videoId);
            return R.ok().message("视频删除成功");
        } catch (ClientException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.VIDEO_DELETE_ALIYUN_ERROR);
        }
    }

    @GetMapping("get-upload-auth-and-address/{title}/{fileName}")
    public R getVideoUploadAuthAndAddress(
            @ApiParam(name="title", value = "视频文件标题", required = true)
            @PathVariable String title,
            @ApiParam(name="fileName", value = "视频文件名称", required = true)
            @PathVariable String fileName){

        try {
            Map<String, Object> map = videoService.getVideoUploadAuthAndAddress(title, fileName);
            return R.ok().data(map).message("获取视频上传地址和凭证成功");
        } catch (ClientException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.FETCH_VIDEO_UPLOADAUTH_ERROR);
        }
    }

    /**
     * 刷新视频上传地址和凭证
     *
     * @param videoId
     * @return
     */
    @GetMapping("refresh-upload-auth/{videoId}")
    public R refreshVideoUploadAuth(
            @ApiParam(name = "videoId", value = "云视频id", required = true)
            @PathVariable String videoId) {
        try {
            Map<String, Object> map = videoService.refreshVideoUploadAuth(videoId);
            return R.ok().data(map).message("刷新视频上传地址和凭证成功");
        } catch (ClientException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.REFRESH_VIDEO_UPLOADAUTH_ERROR);
        }
    }
}
