package org.clxmm.bio05;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/7 1:26 下午
 */
public class Server {


    /**
     * 服务端，接收客户端的任意类型的文件，并保存到服务端的磁盘
     */
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);

            while (true){
                Socket socket = serverSocket.accept();
                // 交给一个独立的线程来处理与这个客户端的文件通信需求。
                new ServerReaderThread(socket).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }




}
