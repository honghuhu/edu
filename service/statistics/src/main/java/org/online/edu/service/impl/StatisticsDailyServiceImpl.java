package org.online.edu.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.online.edu.client.UCenterClient;
import org.online.edu.entity.StatisticsDaily;
import org.online.edu.mapper.StatisticsDailyMapper;
import org.online.edu.service.StatisticsDailyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author 007
 * @since 2020-04-19
 */
@Service
@AllArgsConstructor
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    private UCenterClient uCenterClient;

    @Override
    public void countDay(String preDay) {
        StatisticsDaily statisticsDaily = new StatisticsDaily()
                .setDateCalculated(preDay)
                .setRegisterNum(uCenterClient.countRegister(preDay))
                .setCourseNum(RandomUtil.randomInt(100))
                .setLoginNum(RandomUtil.randomInt(100))
                .setVideoViewNum(RandomUtil.randomInt(100));
        saveOrUpdate(statisticsDaily, new LambdaQueryWrapper<StatisticsDaily>().eq(StatisticsDaily::getDateCalculated, preDay));
    }

    @Override
    public Map<String, List> betweenTime(String type, String begin, String end) {
        List<StatisticsDaily> statisticsDailies = new LambdaQueryChainWrapper<>(baseMapper).between(StatisticsDaily::getDateCalculated, begin, end).list();
        List<String> date = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        for (StatisticsDaily daily : statisticsDailies) {
            date.add(daily.getDateCalculated());
            switch (type) {
                case "register_num":
                    data.add(daily.getRegisterNum());
                    break;
                case "login_num":
                    data.add(daily.getLoginNum());
                    break;
                case "video_view_num":
                    data.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    data.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        return new HashMap<String, List>(2) {{
            put("date", date);
            put("data", data);
        }};
    }
}
