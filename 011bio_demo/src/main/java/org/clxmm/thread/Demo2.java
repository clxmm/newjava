package org.clxmm.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/24 8:39 下午
 */
@Slf4j
public class Demo2 {


    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                log.debug("runner1");
            }
        };

        Thread thread = new Thread(r,"t2");

        thread.start();
    }
}
