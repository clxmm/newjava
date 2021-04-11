package org.clxmm.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/24 8:18 下午
 */
@Slf4j
public class Demo1 {

    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                log.debug("run1");
            }
        };
        t.setName("t1");
        t.start();
        log.debug("main run");
    }
}
