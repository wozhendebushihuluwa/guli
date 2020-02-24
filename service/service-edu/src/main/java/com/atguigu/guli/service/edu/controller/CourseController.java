package com.atguigu.guli.service.edu.controller;


import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.service.edu.entity.form.CourseInfoForm;
import com.atguigu.guli.service.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}

