package org.clxmm;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * @author clx
 * @date 2020-09-07 21:05
 */
public class Test3RandomAccessFile {


    public static void main(String[] args) throws IOException {
        File file  = new File("demo");

        if (!file.exists()) {
            file.mkdirs();
        }


        File f = new File(file,"raf.txt");

        if (!f.exists()) {
            f.createNewFile();
        }

        RandomAccessFile raf = new RandomAccessFile(f,"rw");

        // 指针的位置
        System.out.println(raf.getFilePointer());

        raf.write('A');  // 只写了一个字节
        System.out.println(raf.getFilePointer());
        raf.write('B');
        System.out.println(raf.getFilePointer());


        int i = 0x7fffffff;
        // 用write 方法，每次写一个字节，写四次
        raf.write(i >>> 24);
        raf.write(i >>> 16);
        raf.write(i >>> 8);
        raf.write(i);

        System.out.println(raf.getFilePointer());

        raf.writeInt(i);


        String s = "中";

        byte[] bytes = s.getBytes("utf-8");
        raf.write(bytes);

        System.out.println(raf.length());



        // 读文件 指针移到头部
        raf.seek(0);

        // 一次读取
        byte[] buf = new byte[(int)raf.length()];

        raf.read(buf);

        System.out.println(Arrays.toString(buf));


    }
}
