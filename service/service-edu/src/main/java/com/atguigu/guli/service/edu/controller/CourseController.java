package com.atguigu.guli.service.edu.controller;


import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.service.edu.entity.Course;
import com.atguigu.guli.service.edu.entity.form.CourseInfoForm;
import com.atguigu.guli.service.edu.entity.vo.CoursePublishVo;
import com.atguigu.guli.service.edu.entity.vo.CourseQueryVo;
import com.atguigu.guli.service.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author ZHX
 * @since 2020-02-12
 */
@RestController
@RequestMapping("/admin/edu/course")
@CrossOrigin
@Api(description = "课程管理")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @ApiOperation(value = "新增课程")
    @PostMapping("save-course-info")
    public R saveCourseInfo(
            @ApiParam(name="CourseInfoForm",value = "课程基本信息",required = true)
            @RequestBody CourseInfoForm courseInfoForm
            ){
       String courseId= this.courseService.saveCourseInfo(courseInfoForm);
        return R.ok().data("courseId",courseId);
    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("course-info/{id}")
    public R getById(
            @ApiParam(name = "id",value = "课程ID",required = true)
            @PathVariable String id
    ){
        CourseInfoForm courseInfoForm = this.courseService.getCourseInfoFormById(id);
        return R.ok().data("item",courseInfoForm);
    }

    @ApiOperation(value = "更新课程")
    @PutMapping("update-course-info")
    public R updateCourseInfoById(
            @ApiParam(name = "courseInfoForm",value = "课程基本信息",required = true)
            @RequestBody CourseInfoForm courseInfoForm
    ){
        this.courseService.updateCourseInfoById(courseInfoForm);
        return R.ok();
    }

    @ApiOperation(value = "分页课程列表")
    @GetMapping("{page}/{limit}")
    public R index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQueryVo", value = "查询对象", required = false)
                    CourseQueryVo courseQueryVo){
        Page<Course> pageParam = new Page<>(page, limit);

        courseService.selectPage(pageParam, courseQueryVo);
        List<Course> records = pageParam.getRecords();

        long total = pageParam.getTotal();

        return  R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){
        courseService.removeCourseById(id);
        return R.ok();
    }

    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("course-publish-info/{id}")
    public R getCoursePublishVoById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        CoursePublishVo courseInfoForm = courseService.getCoursePublishVoById(id);
        return R.ok().data("item", courseInfoForm);
    }

    @ApiOperation(value = "根据id发布课程")
    @PutMapping("publish-course/{id}")
    public R publishCourseById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        courseService.publishCourseById(id);
        return R.ok();
    }
}

