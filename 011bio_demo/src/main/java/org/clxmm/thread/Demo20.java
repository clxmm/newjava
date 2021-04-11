package org.clxmm.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/28 8:23 下午
 */
@Slf4j
public class Demo20 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("吸水壶");
            try {
                Thread.sleep(1_000);

                log.debug("烧开水");
                Thread.sleep(5_000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "t1");


        Thread t2 = new Thread(() -> {
            try {
                log.debug("洗茶壶");
                Thread.sleep(1_000);
                log.debug("洗茶杯");
                Thread.sleep(2_000);
                log.debug("拿茶叶");
                Thread.sleep(1_000);
                // 走到这时等待t1 线程完成
                t1.join();
                log.debug("泡茶");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2");

        t1.start();
        t2.start();


    }
}
