package prg.clxmm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/27 8:06 下午
 */
public class Test02Atomicity {


    /**
     * 5个线程各执行1000次 i++;
     * @param args
     */
    private static int number = 0;
    public static void main(String[] args) throws InterruptedException {
        Runnable inc = () -> {
            for (int i = 0; i < 1000; i++) {
                number ++;
            }
        };

        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(inc);
            t.start();
            list.add(t);
        }

        for (Thread thread : list) {
            thread.join();
        }


        System.out.println(number);
    }

}
