package com.atguigu.guli.service.edu.mapper;

import com.atguigu.guli.service.edu.entity.Subject;
import com.atguigu.guli.service.edu.entity.vo.SubjectVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author ZHX
 * @since 2020-02-12
 */
public interface SubjectMapper extends BaseMapper<Subject> {

    List<SubjectVo> selectNestedByParentId(String parentId);
}
