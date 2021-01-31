package prg.clxmm;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/27 7:51 下午
 */
public class Test01Visibility {


    /**
     * 可见性问题
     * 1。创建要一个共享变量
     * 2。创建一条不断读取共享变量
     * 3。创建一条线程修改共享变量
     *
     * 并发编程时，会出现可见性问题，当一个线程对共享变量进行了修改，另外的线程并没有立即看到修改
     * 后的最新值。
     */
    private static boolean flag = true;
    public static void main(String[] args) throws InterruptedException {
        new  Thread(() -> {
            while (flag) {

            }
        }).start();

        Thread.sleep(2_000);
        new  Thread(() -> {
            flag = false;
            System.out.println("修改了flag " + flag);
        }).start();


    }


}
