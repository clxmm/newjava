package org.clxmm.thread;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/28 2:12 下午
 */
public class Demo9 {


    /**
     * sleep  线程状态转换
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread("t1") {
            @Override
            public void run() {

                try {
                    Thread.sleep(2_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        thread.start();
        System.out.println("t1 state" + thread.getState());  // TIMED_WAITING 或者 runnable （有可能主线程先执行）
        Thread.sleep(50);
        System.out.println("t1 state" + thread.getState()); // TIMED_WAITING
    }



}
