package org.online.edu.controller;


import com.baomidou.mybatisplus.extension.api.R;
import lombok.AllArgsConstructor;
import org.online.edu.service.StatisticsDailyService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author 007
 * @since 2020-04-19
 */
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("daily")
public class StatisticsDailyController {

    private StatisticsDailyService statisticsDailyService;

    @GetMapping("{type}/{begin}/{end}")
    public R showChart(@PathVariable String type, @PathVariable String begin, @PathVariable String end) {
        Map map = statisticsDailyService.betweenTime(type, begin, end);
        return R.ok(map);
    }
}

