package prg.clxmm.nature;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/3 7:08 下午
 */
public class Demo1 {


    public static void main(String[] args) {
        new MyThread().start();
        new MyThread().start();
    }


}

// 自定义一个线程类
class MyThread extends Thread {

    @Override
    public void run() {
        synchronized (MyThread.class) {
            System.out.println(getName() + "1");
        }

        synchronized (MyThread.class) {
            System.out.println(getName() + "2");
        }


    }
}