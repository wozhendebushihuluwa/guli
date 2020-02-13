package com.atguigu.guli.service.edu.controller;


import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.entity.vo.TeacherQueryVo;
import com.atguigu.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author ZHX
 * @since 2020-02-12
 */
@RestController
@RequestMapping("admin/edu/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("list")
    public R listAll(){

        List<Teacher> list = this.teacherService.list(null);
        return R.ok().data("items",list).message("讲师列表获取成功");
    }

    @DeleteMapping("remove/{id}")
    public R removeByTeacherId(@PathVariable String id){
        boolean flag = this.teacherService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("list/{page}/{limit}")
    public R index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherQueryVo", value = "讲师查询对象", required = false)
            TeacherQueryVo teacherQueryVo){

        Page<Teacher> pageParam = new Page<Teacher>(page, limit);
        IPage<Teacher> pageModel = teacherService.selectPage(pageParam, teacherQueryVo);
        List<Teacher> records = pageModel.getRecords();
        long total = pageModel.getTotal();

        return  R.ok().data("total", total).data("rows", records).message("获取讲师分页列表成功");
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping("save")
    public R save(
            @ApiParam(name = "teacher",value = "讲师对象",required = true)
            @RequestBody Teacher teacher){
        this.teacherService.save(teacher);
        return R.ok();
    }

    @ApiOperation(value = "根据id查询教师信息")
    @GetMapping("get/{id}")
    public R selectById(
            @ApiParam(name = "id",value = "讲师Id",required = true)
            @PathVariable String id){
        Teacher teacher = this.teacherService.getById(id);
        return R.ok().data("teacher",teacher);
    }
    @ApiOperation(value = "修改教师信息")
    @PutMapping("update")
    public R update(
            @ApiParam(name = "teacher",value = "教师信息",required = true)
            @RequestBody Teacher teacher
    ){
        boolean b = this.teacherService.updateById(teacher);
        return R.ok().message("修改教师信息成功");
    }
}

