package org.clxmm.util;

import java.io.*;

/**
 * @author clx
 * @date 2020-09-08 19:54
 */
public class IoUtil {


    /**
     * 读取指定文件内容 按照16进制输出到控制台
     * 并且每输出10个byte换行
     */
    public static void printHex(String fileName) throws IOException {
        // 把文件作为字节流进行读操作
        FileInputStream in = new FileInputStream(fileName);

        int b;
        int i=1;
        while ((b=in.read())!=-1) {
            if (b<0xf) {
                System.out.print("0"+Integer.toHexString(b) + " ");
            } else {
                System.out.print(Integer.toHexString(b) + " ");
            }
            if (i++%10 == 0) {
                System.out.println();
            }
        }

        in.close();
    }


    public static void printHex1(String fileName) throws IOException {

        FileInputStream in = new FileInputStream(fileName);



        byte[] buf =new byte[20*1024];


        int bytes = in.read(buf,0,buf.length);  // 一次性读完

        int j =1;
        for (int i = 0; i < bytes; i++) {

            if (buf[i] <= 0xf) {
                System.out.print("0");
            }
            System.out.print(Integer.toHexString(buf[i]) + " ");

            if (j++%10 == 0) {
                System.out.println();
            }
        }


        in.close();

    }


    public static void printHex2(String fileName) throws IOException {

        FileInputStream in = new FileInputStream(fileName);



        byte[] buf =new byte[2*1024];


        int bytes = 0;
        int j = 0;
        while ((bytes = in.read(buf,0,buf.length)) != -1) {
            for (int i = 0; i < bytes; i++) {

                if (buf[i] <= 0xf) {
                    System.out.print("0");
                }
                System.out.print(Integer.toHexString(buf[i]) + " ");

                if (j++%10 == 0) {
                    System.out.println();
                }
            }
        }


        in.close();

    }

    public static void copyFile (File srcFile,File destFile) throws  IOException {

        if (!srcFile.exists()) {
            throw  new IllegalArgumentException("文件"+srcFile+"不存在");
        }

        if (!srcFile.isFile()) {
            throw  new IllegalArgumentException("文件"+srcFile+"不是文件");
        }




        FileInputStream in = new FileInputStream(srcFile);
        FileOutputStream out = new FileOutputStream(destFile);

        byte[] buf = new byte[1024];
        int b = 0;

        while ((b=in.read(buf,0,buf.length))!=-1) {

            out.write(buf,0,b);
            out.flush();
        }

        in.close();
        out.close();

    }


    public static void copyFileByBuffered (File srcFile,File destFile) throws  IOException {

        if (!srcFile.exists()) {
            throw new IllegalArgumentException("文件" + srcFile + "不存在");
        }

        if (!srcFile.isFile()) {
            throw new IllegalArgumentException("文件" + srcFile + "不是文件");
        }


        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));

        int c;


        while ((c = bis.read()) != -1) {
            bos.write(c);
            bos.flush(); // 刷新缓冲区
        }

        bis.close();
        bos.close();


    }

    public static void main(String[] args) throws IOException {
//        printHex2("D:\\resourceStudy\\imooc\\newjava\\003-file-io\\src\\main\\java\\org\\clxmm\\Test2File.java");
        copyFileByBuffered(new File("D:\\resourceStudy\\imooc\\newjava\\003-file-io\\src\\main\\java\\org\\clxmm\\Test3RandomAccessFile.java"),
               new File("demo/out1.java"));


    }
}
