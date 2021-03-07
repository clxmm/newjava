package org.clxmm.bio02;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/5 8:32 下午
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9999);

        // 从socket 中获取字节输出流
        OutputStream os = socket.getOutputStream();

        PrintStream ps = new PrintStream(os);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("请说");
            String s = scanner.nextLine();
            ps.println(s);
            ps.flush();
        }

    }

}
