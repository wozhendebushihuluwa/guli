package com.atguigu.guli.service.statistics.service;

import com.atguigu.guli.service.statistics.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author ZHX
 * @since 2020-03-04
 */
public interface DailyService extends IService<Daily> {

    void createStatisticsByDay(String day);
}
