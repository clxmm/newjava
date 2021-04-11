package org.clxmm.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/28 2:04 下午
 */
@Slf4j
public class Demo8 {

    /**
     * 常见的线程的状态
     * @param args
     */
    public static void main(String[] args) {
        Thread thread = new Thread("t1") {
            @Override
            public void run() {
                log.debug("running..");
            }
        };

        System.out.println(thread.getState());  // NEW
        thread.start();
        System.out.println(thread.getState()); // RUNNABLE
//        thread.start();    // java.lang.IllegalThreadStateException start z只能调用一次


    }


}
