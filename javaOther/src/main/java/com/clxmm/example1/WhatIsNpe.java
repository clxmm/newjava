package com.clxmm.example1;


import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;

/**
 *
 * <h1>什么是空指针</h1>
 * @author clxmm
 * @version 1.0
 * @date 2020/9/26 4:48 下午
 */
public class WhatIsNpe {


    public static  class  User {
        private String name;
        private String[] address;


        public void print() {
            System.out.println(" user class");
        }

        public String readBook() {
            System.out.println(" user read book");
            return  null;
        }

    }

    /**
     * 自定义异常
     */
    public  static  class  CustomException extends RuntimeException {

    }

    public static void main(String[] args) {
        // 1. 调用了空对象的实例方法；

        // javac

        User user = null;
        user.print();

        //2. 访问了空对象的属性
//        User user = null;
//        System.out.println(user.name);


        // 3. 数组为空对象时取他的长度
//        User user = new User();
//        System.out.println(user.address.length);

        // 4。null 当作 throwable 的值
//        CustomException customException = null;
//        throw customException;


        // 5。 方法的返回值为null，调用者直接·使用
//        User user = new User();
//        System.out.println(user.readBook().contains("mysql"));




    }





}
