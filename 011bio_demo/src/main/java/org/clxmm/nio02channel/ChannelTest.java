package org.clxmm.nio02channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/8 8:49 下午
 */
public class ChannelTest {

    public static void main(String[] args)  throws  Exception{
//        write();


//        read();


//        copy();


//        test();

        test02();

    }

    private static void test02() throws Exception {
        // 1、字节输入管道
        FileInputStream is = new FileInputStream("data01.txt");
        FileChannel isChannel = is.getChannel();
        // 2、字节输出流管道
        FileOutputStream fos = new FileOutputStream("data04.txt");
        FileOutputStream fos1 = new FileOutputStream("data05.txt");
        FileChannel osChannel = fos.getChannel();
        FileChannel fos1Channel = fos1.getChannel();
        // 3、复制 从目标通道中去复制原通道数据
//        osChannel.transferFrom(isChannel,isChannel.position(),isChannel.size());
        // 、复制  把原通道数据复制到目标通道
        isChannel.transferTo(isChannel.position(),isChannel.size(),fos1Channel);

        isChannel.close();
        osChannel.close();




    }


    private static void test() throws Exception {
        FileInputStream fis = new FileInputStream("data01.txt");
        FileOutputStream fos = new FileOutputStream("data03.txt");

        //2. 分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(4);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);
        ByteBuffer[] buffers = new ByteBuffer[]{buf1,buf2};


        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();

        // 从通道读取数据分散到各个缓冲区
        fisChannel.read(buffers);
        for (ByteBuffer buffer : buffers) {
            buffer.flip();   // 切换到读模式
            System.out.println(new String(buffer.array(),0,buffer.remaining()));
            /**
             * hell
             * o,clxmm!
             */
        }
        // 聚集写入到通道
        fosChannel.write(buffers);
        fisChannel.close();
        fosChannel.close();
    }


    private static void copy() {
        try {
            File file = new File("data01.txt");
            File outFile = new File("date02.txt");

            FileInputStream fis = new FileInputStream(file);

            FileOutputStream fos = new FileOutputStream(outFile);

            FileChannel fisChannel = fis.getChannel();
            FileChannel fosChannel = fos.getChannel();

            // 分配缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                // 先清空缓冲区
                buffer.clear();
                int flag = fisChannel.read(buffer);
                if (flag == -1) {
                    break;
                }
                // 缓冲区切换到可读模式
                buffer.flip();
                fosChannel.write(buffer);
            }

            fisChannel.close();
            fosChannel.close();
        } catch (Exception e) {

        }


    }

    private static void read() {
        try {
            FileInputStream is = new FileInputStream("data01.txt");
            // 得到问价字节输入流的文件通道
            FileChannel channel = is.getChannel();
            // 定义一个缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 读数据到缓冲区
            channel.read(buffer);
            buffer.flip();

            String s = new String(buffer.array(), 0, buffer.remaining());
            System.out.println(s);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void write() {
        try {
            // 1、字节输出流通向目标文件
            FileOutputStream fos = new FileOutputStream("data01.txt");
            // 2、得到字节输出流对应的通道Channel
            FileChannel channel = fos.getChannel();

            // 3、分配缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("hello,clxmm!".getBytes());

            // 4、把缓冲区切换成写出模式
            buffer.flip();
            channel.write(buffer);
            channel.close();
            System.out.println("写数据到文件中！");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
