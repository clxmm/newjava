package org.clxmm.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/28 3:45 下午
 */
@Slf4j
public class Demo15 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("sleep ..");
            try {
                Thread.sleep(5_000);  // wait join 会情况打断标记   重置为false
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t1.start();
        Thread.sleep(1_000);
        log.debug("interrupt");
        t1.interrupt();
        log.debug("" + t1.isInterrupted());
    }

}
