package org.clxmm.bio01;

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
     * 客户端发送消息
     * 服务端接受消息
     */

    public static void main(String[] args) {
        try {

            //1. 定义一个socket 注册端口
            ServerSocket serverSocket = new ServerSocket(9999);
            // 监听客户端的Socket的链接请求
            Socket socket = serverSocket.accept();
            // 3。从Socket中得一个字节输入流对象
            InputStream is = socket.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String msg;

            if ((msg = br.readLine()) != null) {
                System.out.println("服务端接受消息" + msg);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
