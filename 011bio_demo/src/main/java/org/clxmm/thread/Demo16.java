package org.clxmm.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/28 3:52 下午
 */
@Slf4j
public class Demo16 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                boolean interrupted = current.isInterrupted();
                if (interrupted) {
                    log.debug(" 打断状态1: {}", interrupted);
                    break;
                }
            }
        }, "name");
        t1.start();
        Thread.sleep(1_000);
        log.debug(" 打断状态2: {}", t1.isInterrupted());
        t1.interrupt();  // 不会停止
    }
}
