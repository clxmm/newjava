package org.clxmm.bio03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/6 7:17 下午
 */
public class ServerReadThread extends Thread {

    private Socket socket;

    public ServerReadThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String msg;
            while ((msg = br.readLine()) != null) {
                System.out.println(Thread.currentThread().getName() + ";" + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
