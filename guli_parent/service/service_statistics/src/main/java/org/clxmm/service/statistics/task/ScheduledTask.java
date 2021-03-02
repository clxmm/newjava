package org.clxmm.service.statistics.task;

import lombok.extern.slf4j.Slf4j;
import org.clxmm.service.statistics.service.DailyService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/2/28 5:12 下午
 */
@Slf4j
@Component
public class ScheduledTask {

    @Autowired
    private DailyService dailyService;

    /**
     * 每天凌晨1点执行定时任务
     */
    @Scheduled(cron = "0 0 1 * * ?") //注意只支持6位表达式
    public void taskGenarateStatisticsData() {
        String day = new DateTime().minusDays(1).toString("yyyy-MM-dd");
        dailyService.createStatisticsByDay(day);
        log.info("taskGenarateStatisticsData 统计完毕");
    }

}
