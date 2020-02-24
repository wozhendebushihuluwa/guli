package com.atguigu.guli.service.edu.service;

import com.atguigu.guli.service.edu.entity.Subject;
import com.atguigu.guli.service.edu.entity.vo.SubjectVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.util.List;

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

    List<SubjectVo> nestedList();

    List<SubjectVo> nestedList2();
}
