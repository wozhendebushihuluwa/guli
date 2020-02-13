package com.atguigu.guli.service.edu.controller.api;


import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.service.TeacherService;
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
@RequestMapping("api/edu/teacher")
public class ApiTeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("list")
    public List<Teacher> listAll(){
       return this.teacherService.list(null);
    }

}

