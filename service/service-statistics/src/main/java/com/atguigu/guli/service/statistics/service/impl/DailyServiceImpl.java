package com.atguigu.guli.service.statistics.service.impl;

import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.service.statistics.client.UcenterClient;
import com.atguigu.guli.service.statistics.entity.Daily;
import com.atguigu.guli.service.statistics.mapper.DailyMapper;
import com.atguigu.guli.service.statistics.service.DailyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author ZHX
 * @since 2020-03-04
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private UcenterClient ucenterClient;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createStatisticsByDay(String day) {

        //如果当日的统计记录已存在，则删除重新统计|或 提示用户当日记录已存在
        QueryWrapper<Daily> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("date_calculated", day);
        baseMapper.delete(queryWrapper);

        //生成统计记录
        R r = ucenterClient.countRegisterByDay(day);
        Integer registerCount = (Integer)r.getData().get("registerCount");
        int loginCount = RandomUtils.nextInt(100, 200);
        int videoViewCount = RandomUtils.nextInt(100, 200);
        int courseCount = RandomUtils.nextInt(100, 200);

        //在本地数据库创建统计信息
        Daily daily = new Daily();
        daily.setRegisterNum(registerCount);
        daily.setLoginNum(loginCount);
        daily.setVideoViewNum(videoViewCount);
        daily.setCourseNum(courseCount);
        daily.setDateCalculated(day);

        baseMapper.insert(daily);
    }

    @Override
    public Map<String, Object> getChartData(String begin, String end, String type) {

        Map<String, Object> map = new HashMap<>();

//      表 statistics_daily: 列 date_calculated, type，条件 begin、end
        QueryWrapper<Daily> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("date_calculated", type);
        queryWrapper.between("date_calculated", begin, end);
        queryWrapper.orderByAsc("date_calculated");

        //创建列表
        List<String> xList = new ArrayList<>();//日期列表
        List<Integer> yList = new ArrayList<>();//数据列表

        List<Map<String, Object>> mapsData = baseMapper.selectMaps(queryWrapper);
        for (Map<String, Object> data : mapsData) {
            String dateCalculated = (String)data.get("date_calculated");
            Integer count = (Integer)data.get(type);

            xList.add(dateCalculated);
            yList.add(count);
        }

        map.put("xData", xList);
        map.put("yData", yList);
        return map;
    }
}
