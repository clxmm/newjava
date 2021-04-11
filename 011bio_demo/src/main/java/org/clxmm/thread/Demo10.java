package org.clxmm.thread;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/28 2:25 下午
 */
public class Demo10 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread("t1") {
            @Override
            public void run() {
                System.out.println("t1 start sleep ");

                try {
                    Thread.sleep(2_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
        Thread.sleep(1_000);
        System.out.println("main interrupt t1 ");
        thread.interrupt();


    }

}
