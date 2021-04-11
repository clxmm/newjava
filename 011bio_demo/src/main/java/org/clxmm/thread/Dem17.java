package org.clxmm.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/28 4:16 下午
 */
@Slf4j
public class Dem17 {

    /**
     * 两阶段终止模式
     */
    public static void main(String[] args) throws InterruptedException {
        TwoThreadMonitor  t = new TwoThreadMonitor();
        t.start();
        Thread.sleep(3_000);

        t.stop();
    }


}

@Slf4j
class TwoThreadMonitor {

    private Thread monitor;


    public void start() {

        monitor = new Thread(() -> {
            while (true) {
                Thread currentThread = Thread.currentThread();

                if (currentThread.isInterrupted()) {
                    log.debug("料理后事");
                    break;
                }

                try {
                    Thread.sleep(1_000); // 情况1
                    log.debug("执行监控记录");  // 情况2
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    currentThread.interrupt(); //  重新设置打断标记， 因为sleep会清楚打断标记
                }
            }
        });
        monitor.start();
    }

    public void stop() {
        monitor.interrupt();
    }

}