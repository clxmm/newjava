package org.clxmm.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/28 1:59 下午
 */
@Slf4j
public class Demo7 {
    public static void main(String[] args) {

        Thread thread = new Thread("t1") {
            @Override
            public void run() {
                log.debug("running..");
            }
        };

//        thread.run();

        thread.start();
    }

}
