package org.clxmm;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test5FrAndFwDemo {


    public static void main(String[] args) throws IOException {


        FileReader fr = new FileReader("demo/out.java");

        // append 追加
        FileWriter fe = new FileWriter("demo/fw.java",true);

        char[] buffer = new char[10 * 1024];

        int c ;

        while ((c=fr.read(buffer,0,buffer.length)) !=-1) {
            fe.write(buffer,0,c);
            fe.flush();
        }



        fr.close();
        fe.close();
    }


}
