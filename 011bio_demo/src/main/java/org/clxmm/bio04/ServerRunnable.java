package org.clxmm.bio04;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/6 7:59 下午
 */
public class ServerRunnable implements Runnable {

    private Socket socket;

    public ServerRunnable(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            // 读取一行数据
            InputStream is = socket.getInputStream();
            // 转成一个缓冲字符流
            Reader fr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(fr);
            // 一行一行的读取数据
            String line = null;
            while ((line = br.readLine()) != null) { // 阻塞式的！！
                System.out.println("服务端收到了数据：" + line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
