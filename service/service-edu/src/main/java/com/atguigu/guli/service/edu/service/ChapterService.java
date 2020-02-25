package com.atguigu.guli.service.edu.service;

import com.atguigu.guli.service.edu.entity.Chapter;
import com.atguigu.guli.service.edu.entity.vo.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author ZHX
 * @since 2020-02-12
 */
public interface ChapterService extends IService<Chapter> {

    void removeChapterById(String id);

    List<ChapterVo> nestedList(String courseId);
}
