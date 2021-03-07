package org.clxmm.bio02;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/5 8:32 下午
 */
public class Server {

    /**
     *
     * 服务端 可以反复的接收消息
     * 客服端 反复的发送消息
     */
    public static void main(String[] args) {
        try {
            System.out.println("服务端启动");
            //1. 定义一个socket 注册端口
            ServerSocket serverSocket = new ServerSocket(9999);
            // 监听客户端的Socket的链接请求
            Socket socket = serverSocket.accept();
            // 3。从Socket中得一个字节输入流对象
            InputStream is = socket.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String msg;

            while ((msg = br.readLine()) != null) {
                System.out.println("服务端接受消息" + msg);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
