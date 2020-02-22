package com.atguigu.guli.service.edu.service;

import com.atguigu.guli.service.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author ZHX
 * @since 2020-02-12
 */
public interface SubjectService extends IService<Subject> {

    void batchImport(InputStream inputStream) throws Exception;
}
