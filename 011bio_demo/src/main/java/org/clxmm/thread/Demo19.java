package org.clxmm.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/28 8:00 下午
 */
@Slf4j
public class Demo19 {
    /**
     * 线程对应的六种状态
     */

    public static void main(String[] args) {

        Thread t1 = new Thread("t1") {  // NEW
            @Override
            public void run() {
                log.debug("running ");
            }
        };

//        t1.start();


        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                while (true) {  // RUNNABLE

                }
            }
        };

        t2.start();

        Thread t3 = new Thread("t3") {
            @Override
            public void run() {   // TERMINATED
                log.debug("running ");
            }
        };
        t3.start();

        Thread t4 = new Thread("t4") {
            @Override
            public void run() {
                synchronized (Demo19.class) {
                    try {
                        Thread.sleep(1000_000);  // TIMED_WAITING
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t4.start();
        Thread t5 = new Thread("t5") {
            @Override
            public void run() {
                try {
                    t2.join(); // waiting
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t5.start();

        Thread t6 = new Thread("t6") {
            @Override
            public void run() {
                synchronized (Demo19.class) {
                    try {
                        Thread.sleep(100_000);  //BLOCKED
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        t6.start();

        log.debug("t1: {}", t1.getState());
        log.debug("t2: {}", t2.getState());
        log.debug("t3: {}", t3.getState());
        log.debug("t4: {}", t4.getState());
        log.debug("t5: {}", t5.getState());
        log.debug("t6: {}", t6.getState());

    }


}
