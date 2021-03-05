package prg.clxmm.nature;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/3 7:31 下午
 */
public class Demo03_Interruptible {
    //演示Lock不可中断和可中断

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
//        test01();
        test02();
    }

    //可中断
    public static void test02() throws InterruptedException {
        Runnable run = () -> {
            String name = Thread.currentThread().getName();
            boolean b = false;
            try {
                b = lock.tryLock(3, TimeUnit.SECONDS);
                if (b) {
                    System.out.println(name + "获得锁,进入锁执行");
                    Thread.sleep(88888);
                } else {
                    System.out.println(name + "在指定时间没有得到锁做其他操作");
                }
                Thread.sleep(8888);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (b) {
                    lock.unlock();
                    System.out.println(name + " unlock");
                }
            }


        };
        Thread t1 = new Thread(run);
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(run);
        t2.start();
        /**
         * Thread-0获得锁,进入锁执行
         * Thread-1在指定时间没有得到锁做其他操作
         */
//        System.out.println("停止t2线程前");
//        t2.interrupt();
//        System.out.println("停止t2线程后");
//        Thread.sleep(1000);
//        System.out.println(t1.getState());
//        System.out.println(t2.getState());

        /**
         *  输出
         * 停止t2线程前
         * 停止t2线程后
         * java.lang.InterruptedException
         * 	at java.util.concurrent.locks.AbstractQueuedSynchronizer.doAcquireNanos(AbstractQueuedSynchronizer.java:936)
         * 	at java.util.concurrent.locks.AbstractQueuedSynchronizer.tryAcquireNanos(AbstractQueuedSynchronizer.java:1247)
         * 	at java.util.concurrent.locks.ReentrantLock.tryLock(ReentrantLock.java:442)
         * 	at prg.clxmm.nature.Demo03_Interruptible.lambda$test02$0(Demo03_Interruptible.java:28)
         * 	at java.lang.Thread.run(Thread.java:748)
         * TIMED_WAITING
         * TERMINATED
         */

    }

    // Lock不可中断
    public static void test01() throws InterruptedException {
        String name = Thread.currentThread().getName();
        Runnable run = () -> {
            try {

                lock.lock();
                Thread.sleep(888888);
                System.out.println(name + "获得锁，进入锁执行");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(name + "unlock");
            }
        };

        Thread t1 = new Thread(run);
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(run);
        t2.start();


        System.out.println("停止t2线程前");
        t2.interrupt();
        System.out.println("停止t2线程后"); //
        Thread.sleep(1000);
        System.out.println(t1.getState());
        System.out.println(t2.getState());
        /**
         * 输出
         * 停止t2线程前
         * 停止t2线程后
         * TIMED_WAITING
         * WAITING
         */


    }


}
