package org.clxmm.util;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author clx
 * @date 2020-09-08 20:13
 */
public class FileOutUtil {


    public static void main(String[] args) throws IOException {


        // 如果问不存在 则创建， 如果存在，删除后创建
        FileOutputStream out = new FileOutputStream("demo/out.txt");

        out.write('A');  // 写A 的低8位
        int a = 10;  // write只能写8位 写一个int需要4次
        out.write(a >>> 24);
        out.write(a >>> 16);
        out.write(a >>> 8);
        out.write(a);

        byte[] gbk = "中".getBytes("gbk");
        out.write(gbk);


        out.close();


        IoUtil.printHex("demo/out.txt");

    }








}
