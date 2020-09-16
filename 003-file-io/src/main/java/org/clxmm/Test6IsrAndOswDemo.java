package org.clxmm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test6IsrAndOswDemo {

    public static void main(String[] args) throws IOException {

        FileInputStream in = new FileInputStream("demo/out.java");
        InputStreamReader isr = new InputStreamReader(in);

        int c;

//        while ((c=isr.read())!=-1) {
//            System.out.println((char)c);
//        }


        char[] buffer = new char[7*1024];
        int c1;
        // 返回的是读到的个数
        while ((c1= isr.read(buffer,0,buffer.length))!=-1) {
            String s = new String(buffer,0,c1);
            System.out.println(s);
        }


        isr.close();
        in.close();
    }
}
