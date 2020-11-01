package org.clxmm.springboot03task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/31 4:50 下午
 */
@Service
public class ScheduleService {


    /**
     * second minute hour  day of month   month  day of week
     *
     * 0 * * * * MON-FRI
     */
//    @Scheduled(cron = "0 * * * * *")
    // 每4秒启动一次
//    @Scheduled(cron = "0/4 * * * * *")
    public void  hello () {
        System.out.println( "hello ,," + new Date());
    }






}
