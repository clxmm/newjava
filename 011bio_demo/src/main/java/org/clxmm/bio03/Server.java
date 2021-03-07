package org.clxmm.bio03;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/6 6:58 下午
 */
public class Server {

    /**
     * 服务端可以接收 多个客户端socket 请求
     * 服务端每接收一个socket 请求 都会交给一个独立的线程来处理
     */
    public static void main(String[] args) {

        try {
            System.out.println("==服务器的启动==");
            ServerSocket serverSocket = new ServerSocket(9999);
            // 利用循环不断的接收客户端的socket  的链接请求
            while (true) {
                Socket socket = serverSocket.accept();
                //创建一个独立的线程来处理与这个客户端请求的处理
                new ServerReadThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}



