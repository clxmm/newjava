package org.clxmm;

import java.io.*;

/**
 * 对文件的读写操作
 *
 * @author clxmm
 * @version 1.0
 * @date 2020/9/15 8:29 下午
 */
public class Test5brAndbwOrpwDemo {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("demo/out.java")));


        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("demo/bw.java")));
        PrintWriter pw = new PrintWriter("demo/pw.java");


        String line;

        while ((line = br.readLine()) != null) {
            System.out.println(line);   // 一次读一行不识别换行符号
            bw.write(line);
            bw.newLine();  // 单独写出换行操作


            pw.println(line);
            pw.flush();
            bw.flush();
        }

        br.close();
        bw.close();
        pw.close();

    }
}
