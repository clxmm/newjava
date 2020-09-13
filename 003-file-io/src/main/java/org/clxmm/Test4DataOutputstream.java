package org.clxmm;

import org.clxmm.util.IoUtil;

import javax.print.Doc;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author clx
 * @date 2020-09-08 20:31
 */
public class Test4DataOutputstream {


    public static void main(String[] args)  throws IOException {
        String file = "demo/dos.txt";
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));

        dos.writeInt(10);
        dos.writeInt(-10);
        dos.writeLong(10L);
        dos.writeUTF("测试");
        // utf - 16be
        dos.writeChars("测试");


        dos.close();
        IoUtil.printHex(file);
    }
}
