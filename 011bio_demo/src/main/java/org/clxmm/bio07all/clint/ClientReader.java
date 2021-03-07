package org.clxmm.bio07all.clint;

import org.clxmm.bio07all.util.Constants;

import java.io.DataInputStream;
import java.net.Socket;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/7 5:10 下午
 */
public class ClientReader extends Thread {

    private Socket socket;
    // 接收客户端界面，方便收到消息后，更新界面数据。
    private ClientChat clientChat;

    public ClientReader(ClientChat clientChat, Socket socket) {
        this.clientChat = clientChat;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            /** 循环一直等待客户端的消息 */
            while (true) {
                /** 读取当前的消息类型 ：登录,群发,私聊 , @消息 */
                int flag = dis.readInt();
                if (flag == 1) {
                    // 在线人数消息回来了
                    String nameDatas = dis.readUTF();
                    // 展示到在线人数的界面
                    String[] names = nameDatas.split(Constants.SPILIT);

                    clientChat.onLineUsers.setListData(names);
                } else if (flag == 2) {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
