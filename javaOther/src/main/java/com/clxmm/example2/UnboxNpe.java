package com.clxmm.example2;


/**
 * <h1>自动拆箱引发的空指针</h1>
 *
 * @author clxmm
 * @version 1.0
 * @date 2020/9/26 5:28 下午
 */
public class UnboxNpe {

    public static int add(int x , int y) {
        return x + y;
    }
    public static  boolean comare(long x,long y) {
        return x >= y;
    }


    @SuppressWarnings("all")
    public static void main(String[] args) {

        // 1. 变量赋值，自动拆箱出现的空指针
        // javac
        // javap -c

        Long count = null;
        long _count = count;

        // 2. 方法传参时自动拆箱出现的空指针
//        Integer x1 = null;
//        Integer x2 = null;
//        System.out.println(add(x1,x2));



        // 3. 使用大小比较场景
//        Long x = 10L;
//        Long y = null;
//        System.out.println(comare(x,y));





    }
}
