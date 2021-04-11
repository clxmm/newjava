package org.clxmm.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/28 4:40 下午
 */
@Slf4j
public class Demo18 {

    public static void main(String[] args) {
        log.debug("开始运行...");
        Thread t1 = new Thread(() -> {
            log.debug("开始运行...");
            try {
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("运行结束...");
        }, "daemon");
        // 设置该线程为守护线程
        t1.setDaemon(true);
        t1.start();
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("运行结束...");
    }
}
