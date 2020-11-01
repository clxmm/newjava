package org.clxmm.springboot03task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/31 4:37 下午
 */
@Service
public class AsyncService {


    @Async  //异步方法
    public String hello() {
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello";
    }
}
