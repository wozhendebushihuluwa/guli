package com.atguigu.guli.service.edu.service;

import com.atguigu.guli.service.edu.entity.Course;
import com.atguigu.guli.service.edu.entity.form.CourseInfoForm;
import com.atguigu.guli.service.edu.entity.vo.CourseQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author ZHX
 * @since 2020-02-12
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoFormById(String id);

    void updateCourseInfoById(CourseInfoForm courseInfoForm);

    void selectPage(Page<Course> pageParam, CourseQueryVo courseQueryVo);

    void removeCourseById(String id);
}
