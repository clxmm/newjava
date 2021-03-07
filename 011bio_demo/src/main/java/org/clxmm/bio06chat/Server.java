package org.clxmm.bio06chat;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/7 2:08 下午
 */
public class Server {
    /**
     * bio 模式下的端口转发
     * 服务端：
     *  注册端口
     *  接收客户端的socket 连接，交给一个独立的线程来处理
     *  把当前连接的客户端socket 存到一个socket集合中
     *  接收客户端的消息，推送非当前所有在线的socket 接收
     */

    public  static List<Socket> socketList = new ArrayList<>();
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            while (true) {
                Socket accept = serverSocket.accept();
                // 把登陆的客户端socket 存入到一个集合中
                socketList.add(accept);
                // 当前登陆成功的socket中 分配一个独立的线程来处理与之通信
                new  ServerReadThread(accept).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
