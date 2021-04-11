package org.clxmm.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/28 4:31 下午
 */
@Slf4j
public class Demo17 {

    public static void main(String[] args) throws InterruptedException {
        test3();
    }

    private static void test3() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("park...");
            LockSupport.park();
            log.debug("unpark...");
            log.debug("打断状态：{}", Thread.currentThread().isInterrupted());
            Thread.interrupted();
            LockSupport.park();  //
            log.debug("unpark...");
        }, "t1");
        t1.start();
        Thread.sleep(5_000);
        t1.interrupt();
    }
}
