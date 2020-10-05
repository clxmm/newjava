package com.clxmm.example6Try;

import java.io.*;
import java.util.concurrent.BrokenBarrierException;

/**
 * <h1> 解决使用 try finally 资源泄漏隐患</h1>
 *
 * @author clxmm
 * @version 1.0
 * @date 2020/9/29 7:42 下午
 */
public class Main {

    /**
     * 传统的方式
     */
    private String test1() throws IOException {
        // 单一资源的关闭
   /*     BufferedReader reader = new BufferedReader(new FileReader(""));

        try {
            return reader.readLine();
        }
        finally {
            reader.close();
        }*/

        // 多个资源的关闭


        InputStream inputStream = new FileInputStream("");
        try {
            OutputStream out = new FileOutputStream("");

            try {
                byte[] buf = new byte[100];
                int n;
                while ((n = inputStream.read(buf)) >= 0) {
                    out.write(buf, 0, n);
                }
            } finally {
                out.close();
            }

        } finally {
            inputStream.close();
        }


        return null;


    }


    /**
     * java 7   自动资源关闭
     * @return
     * @throws IOException
     */
    private String test2() throws IOException {


        try (BufferedReader br = new BufferedReader(new FileReader(""))){
            String s = br.readLine();
        }


        // 多个资源的关闭
        try (
                FileInputStream in = new FileInputStream("");
                FileOutputStream out = new FileOutputStream("")
                ){

            byte[] buf = new byte[100];
            int n;
            while ((n=in.read(buf)) != -1) {
                out.write(buf,0,n);
            }
        }


        return null;
    }


    public static void main(String[] args) throws  Exception {
        AutoClose autoClose = new AutoClose();

        // finally 会抑制try中的异常
//        try {
//            autoClose.word();
//        } finally {
//            autoClose.close();
//        }


        try (AutoClose autoClose1 = new AutoClose()){

            autoClose.word();
        }






    }

}
