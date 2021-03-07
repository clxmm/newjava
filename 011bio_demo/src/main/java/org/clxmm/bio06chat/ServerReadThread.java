package org.clxmm.bio06chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/7 2:16 下午
 */
public class ServerReadThread extends Thread {
    private Socket socket;

    public ServerReadThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 1. 从socket 中获取当前客户端的输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg;

            while ((msg = br.readLine()) != null) {
                // 2. 接收到客户端消息后，推送给所有的在想socket
                senMsgToAllClient(msg);
            }
        } catch (Exception e) {
            System.out.println("有人下线");
            Server.socketList.remove(socket);
            e.printStackTrace();
        }
    }

    /**
     * 把消息推送给所有的socket
     */
    private void senMsgToAllClient(String msg) {
        List<Socket> socketList = Server.socketList;

        socketList.forEach((e) -> {
            try {
                PrintStream ps = new PrintStream(e.getOutputStream());
                ps.println(msg);
                ps.flush();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }
}
