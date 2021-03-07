package org.clxmm.bio04;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/6 7:25 下午
 */
public class Client {


    public static void main(String[] args) {
        System.out.println("==客户端的启动==");
        try {
            Socket socket = new Socket("127.0.0.1",9999);
            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("请说：");
                String s = scanner.nextLine();
                printStream.println(s);
                printStream.flush();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
