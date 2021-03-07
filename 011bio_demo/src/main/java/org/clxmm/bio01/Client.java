package org.clxmm.bio01;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/5 8:32 下午
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",9999);

        // 从socket 中获取字节输出流
        OutputStream os = socket.getOutputStream();


        PrintStream ps = new PrintStream(os);

        ps.print("11");
//        ps.println("111");
//        ps.println("hello");
        ps.flush();









    }

}
