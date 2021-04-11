package org.clxmm.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/26 8:17 下午
 */
@Slf4j
public class Demo5 {

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                log.debug("running1-----");
            }
        },"t1").start();

        new Thread(() -> {
            while (true) {
                log.debug("running2-----");
            }
        },"t2").start();
    }
}
