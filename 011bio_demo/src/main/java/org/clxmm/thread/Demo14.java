package org.clxmm.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/28 3:27 下午
 */
@Slf4j
public class Demo14 {

    /**
     * join
     * join(int long)
     */

    static int r1 = 0;
    static int r2 = 0;

    public static void main(String[] args) throws InterruptedException {
//        test2();
        test3();
    }

    private static void test3() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            r1 = 10;
        });

        long start = System.currentTimeMillis();
        t1.start();
        // 线程执行结束会导致 join 结束
//        t1.join(1500);  // r1: 0 r2: 0 cost: 1504
        t1.join(3000);
        long end = System.currentTimeMillis(); // r1: 10 r2: 0 cost: 2004
        log.debug("r1: {} r2: {} cost: {}", r1, r2, end - start);
    }

    private static void test2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1);
                r1 = 10;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(2);
                r2 = 20;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        });
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long end = System.currentTimeMillis();
        log.debug("r1: {} r2: {} cost: {}", r1, r2, end - start);
    }
}
