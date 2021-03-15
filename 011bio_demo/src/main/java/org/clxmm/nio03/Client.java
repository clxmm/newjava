package org.clxmm.nio03;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/13 4:15 下午
 */
public class Client {
    public static void main(String[] args) throws Exception {
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));

        channel.configureBlocking(false);

        //3. 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //4. 发送数据给服务端
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("输入：" );
            String str = scan.nextLine();
            buf.put((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(System.currentTimeMillis())
                    + "\n" + str).getBytes());
            buf.flip();
            channel.write(buf);
            buf.clear();
        }
        //5. 关闭通道


    }
}
