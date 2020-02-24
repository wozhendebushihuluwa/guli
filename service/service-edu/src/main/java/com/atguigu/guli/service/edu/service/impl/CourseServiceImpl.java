package com.atguigu.guli.service.edu.service.impl;

import com.atguigu.guli.service.edu.entity.Course;
import com.atguigu.guli.service.edu.entity.CourseDescription;
import com.atguigu.guli.service.edu.entity.form.CourseInfoForm;
import com.atguigu.guli.service.edu.mapper.CourseDescriptionMapper;
import com.atguigu.guli.service.edu.mapper.CourseMapper;
import com.atguigu.guli.service.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author ZHX
 * @since 2020-02-12
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        //保存course表
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm,course);
        course.setStatus(Course.COURSE_DRAFT);
        baseMapper.insert(course);
        //保存course_description表
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionMapper.insert(courseDescription);
        return course.getId();
    }
}
