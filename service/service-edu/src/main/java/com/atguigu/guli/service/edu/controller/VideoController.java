package com.atguigu.guli.service.edu.controller;


import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.service.edu.entity.form.VideoInfoForm;
import com.atguigu.guli.service.edu.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author ZHX
 * @since 2020-02-12
 */
@RestController
@RequestMapping("admin/edu/video")
@CrossOrigin
@Api(description = "课时管理")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @ApiOperation(value = "课时保存")
    @PostMapping("save")
    public R save(
            @ApiParam(name = "videoInfoForm",value = "课时信息",required = true)
            @RequestBody VideoInfoForm videoInfoForm
            ){
        this.videoService.saveVideoInfo(videoInfoForm);
        return R.ok().message("课时保存成功");
    }

    @ApiOperation("根据ID查询课时")
    @GetMapping("get/{id}")
    public R getVideoInfoById(
            @ApiParam(name = "id",value = "课时ID",required = true)
            @PathVariable String id
    ){
        VideoInfoForm videoInfoForm = this.videoService.getVideoInfoFormById(id);
        return R.ok().data("item",videoInfoForm);
    }

    @ApiOperation(value = "更新课时")
    @PutMapping("update")
    public R updateCourseInfoById(
            @ApiParam(name = "VideoInfoForm", value = "课时基本信息", required = true)
            @RequestBody VideoInfoForm videoInfoForm){

        videoService.updateVideoInfoById(videoInfoForm);
        return R.ok().message("课时修改成功");
    }

    @ApiOperation(value = "根据ID删除课时")
    @DeleteMapping("remove/{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){

        videoService.removeVideoById(id);
        return R.ok();
    }
}

