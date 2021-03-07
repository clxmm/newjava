package org.clxmm.bio04;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/6 7:44 下午
 */
public class Server {

    /**
     * 伪异步通信架构
     *
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);

        while (true) {
            Socket socket = serverSocket.accept();
            HandlerSocketThreadPool handlerSocketThreadPool = new HandlerSocketThreadPool(6,10);
            // socket交给一个线程池处理
            Runnable runnable = new ServerRunnable(socket);
            handlerSocketThreadPool.execute(runnable);
        }


    }

}
