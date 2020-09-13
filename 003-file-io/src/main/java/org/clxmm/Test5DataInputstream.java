package org.clxmm;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author clx
 * @date 2020-09-08 20:35
 */
public class Test5DataInputstream {

    public static void main(String[] args)  throws IOException {
        String file = "demo/dos.txt";

        DataInputStream das = new DataInputStream(new FileInputStream(file));


        int i = das.readInt();
        System.out.println(i);

        i = das.readInt();
        System.out.println(i);


        Long l = das.readLong();
        System.out.println(l);

        String s = das.readUTF();
        System.out.println(s);

        char c = das.readChar();
        System.out.println(c);


        das.close();

    }
}
