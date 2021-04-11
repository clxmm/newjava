package org.clxmm.thread;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/28 2:35 下午
 */
public class Demo12 {

    /**
     * 线程优先级
     */
    public static void main(String[] args) {
        Runnable r1 = () -> {
            int count = 0;

            while (true) {
                System.out.println("--->" + count++);
            }
        };

        Runnable r3 = () -> {
            int count = 0;

            while (true) {
                Thread.yield();
                System.out.println("t2--->" + count++);
            }
        };

        Thread t1 = new Thread(r1, "t1");
        Thread t2 = new Thread(r3, "t2");

        t1.setPriority(Thread.MIN_PRIORITY);
        t1.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
    }
}
