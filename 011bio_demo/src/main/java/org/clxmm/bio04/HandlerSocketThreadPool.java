package org.clxmm.bio04;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/6 7:50 下午
 */
//线程池处理类
public class HandlerSocketThreadPool {
    // 1, 创建一个线程池的成员变量，用于存储一个线程池对象
    private ExecutorService executor;

    public HandlerSocketThreadPool(int maxPoolSize, int queueSize) {
        this.executor = new ThreadPoolExecutor(
                3, // 8
                maxPoolSize,
                120L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize));
    }


    /**
     * 3. 提供一个方法，提交任务给线程池的任务队列，等着线程池来处理
     */
    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }


}
