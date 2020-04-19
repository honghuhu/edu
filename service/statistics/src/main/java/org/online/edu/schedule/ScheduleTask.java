package org.online.edu.schedule;

import lombok.AllArgsConstructor;
import org.online.edu.service.StatisticsDailyService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class ScheduleTask {

    private StatisticsDailyService statisticsDailyService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void countDay() {
        String preDay = LocalDate.now().plusDays(-1).toString();
        statisticsDailyService.countDay(preDay);
    }
}
