package com.clxmm;

import sun.tools.java.ClassNotFound;

/**
 * <h1>异常处理</h1>
 * @author clxmm
 * @version 1.0
 * @date 2020/9/27 8:46 下午
 */
public class example4Exception {
    public static class User {}


    /**
     * <h2> 异常本质 --- 抛出异常</h2>
     */
    private void throwException() {
        User user = null;
        // ...
        if (null == user) {
            throw  new NullPointerException();
        }
    }

    /**
     * <h2>不能捕获空指针异常</h2>
     */
    private void canNotCatchNpeException () {
        try {
            throwException();
        } catch (ClassCastException cce) {
            System.out.println(cce.getMessage());
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
        }


    }

    public static void main(String[] args) {
        example4Exception exception = new example4Exception();

        exception.canNotCatchNpeException();






    }





}
