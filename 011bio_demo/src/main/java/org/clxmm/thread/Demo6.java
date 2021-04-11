package org.clxmm.thread;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/26 8:43 下午
 */
public class Demo6 {
    /**
     * 栈与栈帧
     */

    public static void main(String[] args) {

        Thread thread = new Thread() {
            @Override
            public void run() {
                method1(20);
            }
        };
        thread.setName("t1");
        thread.start();
        method1(10);
    }

    private static void method1(int x) {
        int y = x + 1;
        Object o = method2();
        System.out.println(o);
    }
    private static Object method2() {
        Object o = new Object();
        return o;
    }



}
