package org.clxmm.bio05;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/7 1:28 下午
 */
public class Client {

    /**
     * 客户端上传任意文件类型的文件数据非服务端保存起来
     */
    public static void main(String[] args) {
        try (
                InputStream is = new FileInputStream("/Users/yuanmengen/Documents/图片/柯南.jpg")
        ) {
            Socket socket = new Socket("127.0.0.1", 9999);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(".jpg");
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) > 0) {
                dos.write(buffer, 0, len);
            }
            dos.flush();
            socket.shutdownOutput();   // 通知服务端数据发送完毕
            Thread.sleep(10000);
        } catch (Exception e) {


            e.printStackTrace();


        }

    }

}
