package org.clxmm.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/28 2:29 下午
 */
@Slf4j
public class Demo11 {

    /**
     * TimeUnit 休眠
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        log.debug("start");
        TimeUnit.SECONDS.sleep(1);
        log.debug("end");
    }


}
