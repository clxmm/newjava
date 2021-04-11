package org.clxmm.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/26 8:07 下午
 */
@Slf4j(topic = "c.Demo4")
public class Demo4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                log.debug("running ");
                Thread.sleep(3_000);
                return 100;
            }
        });

        Thread thread = new Thread(task, "t1");

        thread.start();

        // 等待 t1 线程返回的结果 阻塞
        Integer integer = task.get();
        log.debug("{}", integer);
    }


}
