package org.online.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.online.edu.entity.StatisticsDaily;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author 007
 * @since 2020-04-19
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void countDay(String preDay);

    Map betweenTime(String type, String begin, String end);
}
