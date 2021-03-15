package org.clxmm.nio01;

import java.nio.ByteBuffer;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/8 8:07 下午
 */
public class BufferTest {


    public static void main(String[] args) {
//        test1();


//        test2();

        test3();

    }

    public static void test3() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        System.out.println(buffer.isDirect());  //

    }

    public static void test2() {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        String name = "clxmhui";
        buffer.put(name.getBytes());
        buffer.flip();
        // get
        byte[] b = new byte[2];

        buffer.get(b);
        String s = new String(b);
        System.out.println(s); //cl
        System.out.println(buffer.position());  //2
        System.out.println(buffer.limit());   // 3
        System.out.println(buffer.capacity());   //10
        System.out.println("-----------------");

        buffer.mark();   // 标记此刻这个位置       2


        byte[] b2 = new byte[3];

        buffer.get(b2);
        String s2 = new String(b2);
        System.out.println(s2); //xmh
        System.out.println(buffer.position());  //5
        System.out.println(buffer.limit());   // 7
        System.out.println(buffer.capacity());   //10
        System.out.println("-----------------");

        buffer.reset();   // 回到标记的位置

        // 是否有剩下的数据
        if (buffer.hasRemaining()) {
            System.out.println(buffer.remaining());  // 5
        }


    }

    public static void test1() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.position());  //0
        System.out.println(buffer.limit());   // 10
        System.out.println(buffer.capacity());   //10
        System.out.println("----");

        String name = "test11";
        buffer.put(name.getBytes());
        System.out.println(buffer.position());  //6
        System.out.println(buffer.limit());   // 10
        System.out.println(buffer.capacity());   //10
        System.out.println("----");

        // 为 将缓冲区的界限设置为当前位置，并将当前位置充值为 0 : 可读模式，
        buffer.flip();

        System.out.println(buffer.position());  //0
        System.out.println(buffer.limit());   // 6
        System.out.println(buffer.capacity());   //10

        // 读取数据
        char ch = (char) buffer.get();
        System.out.println(ch);   //t
        System.out.println(buffer.position());  //1
        System.out.println(buffer.limit());   // 6
        System.out.println(buffer.capacity());   //10

        System.out.println("----");
        // clear() : 清空缓冲区. 但是缓冲区中的数据依然存在，但是处于“被遗忘”状态
        buffer.clear();
        System.out.println(buffer.position());  //0
        System.out.println(buffer.limit());   // 10
        System.out.println(buffer.capacity());   //10
        System.out.println((char) buffer.get());  // t

    }
}
