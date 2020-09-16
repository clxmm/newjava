package org.clxmm.TestSerizable;

import java.io.*;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/16 8:23 下午
 */
public class Test2ObjectSerializable {


    public static void main(String[] args)  throws Exception{


//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("demo/obj1.dat"));
//
//        Foo2 foo2 = new Foo2();
//        oos.writeObject(foo2);
//        oos.flush();
//        oos.close();
        /** 序列化时递归调用父类的函数
         * foo
         * foo1
         * foo2
         */


        // 反序类化  不调用父类的构造函数
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("demo/obj1.dat"));
//        Foo2 foo21 = (Foo2) ois.readObject();
//        System.out.println(foo21);
//        ois.close();



//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("demo/obj1.dat"));
//        Bar2 bar2 = new Bar2();
//        oos.writeObject(bar2);
//        oos.close();
        /**
         * bar
         * bar1
         * bar2
         */
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("demo/obj1.dat"));

        Bar2 bar21 = (Bar2) ois.readObject();
        System.out.println(bar21);
        ois.close();
        /**
         * bar   父类的构造函数被调用
         *
         * org.clxmm.TestSerizable.Bar2@5cc7c2a6
         */


    }


}

/**
 * 对子类对象进行反序列化时
 * 如果父类没有实现序列化接口，那么父类的构造函数会被调用
 */

class Bar {
    public  Bar () {
        System.out.println("bar ");
    }
}

class Bar1 extends Bar implements Serializable {
    public Bar1() {
        System.out.println("bar1 ");
    }
}

class Bar2 extends Bar1 {
    public Bar2() {
        System.out.println("bar2 ");
    }
}








class Foo implements Serializable {
   public Foo () {
       System.out.println("foo ");
   }
}

class Foo1 extends Foo {
    public Foo1() {
        System.out.println("foo1 ");
    }
}

class  Foo2 extends Foo1 {
    public Foo2 () {
        System.out.println("foo2 ");
    }
}
