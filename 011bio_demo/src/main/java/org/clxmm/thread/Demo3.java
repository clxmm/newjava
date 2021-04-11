package org.clxmm.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/24 8:41 下午
 */
@Slf4j
public class Demo3 {
    public static void main(String[] args) {
        Runnable r = () -> log.debug("hello");

        Thread t = new Thread(r,"t3");

        t.start();
    }

}
