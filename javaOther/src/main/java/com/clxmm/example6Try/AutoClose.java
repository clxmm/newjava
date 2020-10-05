package com.clxmm.example6Try;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/29 7:58 下午
 */
public class AutoClose implements AutoCloseable {

    @Override
    public void close() throws Exception {
        System.out.println(">>>> close()");
        throw  new RuntimeException("exception in close()");
    }

    public void word() throws MyException {
        System.out.println(">>> work()");
        throw  new MyException("exception in work()");
    }

}
