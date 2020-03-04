package com.atguigu.guli.service.statistics.controller;


import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.service.statistics.service.DailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author ZHX
 * @since 2020-03-04
 */
@Api(description="统计分析管理")
@CrossOrigin
@RestController
@RequestMapping("/admin/statistics/daily")
public class DailyController {

    @Autowired
    private DailyService dailyService;

    @ApiOperation(value = "生成统计记录", notes = "生成统计记录")
    @PostMapping("create/{day}")
    public R createStatisticsByDate(
            @ApiParam(name = "day", value = "统计日期")
            @PathVariable String day) {
        dailyService.createStatisticsByDay(day);
        return R.ok().message("数据统计生成成功");
    }

    @GetMapping("show-chart/{begin}/{end}/{type}")
    public R showChart(@PathVariable String type, @PathVariable String begin, @PathVariable String end){
        Map<String, Object> map = dailyService.getChartData(begin, end, type);
        return R.ok().data(map);
    }
}

