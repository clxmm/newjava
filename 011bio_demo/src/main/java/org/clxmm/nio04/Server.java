package org.clxmm.nio04;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/13 6:37 下午
 */
public class Server {
    private static final int PORT = 9999;
    /**
     * nio 的群聊系统
     */
    //定义属性
    private Selector selector;
    private ServerSocketChannel ssChannel;

    //构造器
    //初始化工作
    public Server() {
        try {
            // 1、获取通道
            ssChannel = ServerSocketChannel.open();
            // 2、切换为非阻塞模式
            ssChannel.configureBlocking(false);
            // 3、绑定连接的端口
            ssChannel.bind(new InetSocketAddress(PORT));
            // 4、获取选择器Selector
            selector = Selector.open();
            // 5、将通道都注册到选择器上去，并且开始指定监听接收事件
            ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        //创建服务器对象
        Server groupChatServer = new Server();
        groupChatServer.listen();
    }

    public void listen() {
        System.out.println("监听线程: " + Thread.currentThread().getName());

        try {
            while (selector.select() > 0) {
                System.out.println("开始一轮事件处理~~~");
                // 7、获取选择器中的所有注册的通道中已经就绪好的事件
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    // 提取当前这个事件
                    SelectionKey sk = it.next();
                    // 事件类型
                    if (sk.isAcceptable()) {  //客户端接入请求
                        SocketChannel socketChannel = ssChannel.accept();
                        socketChannel.configureBlocking(false);
                        // 12、将本客户端通道注册到选择器
                        System.out.println(socketChannel.getRemoteAddress() + " 上线 ");
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (sk.isReadable()) {
                        //处理读 (专门写方法..)
                        readData(sk);
                    }
                    it.remove(); // 处理完毕之后需要移除当前事件
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //发生异常处理....

        }


    }

    private void readData(SelectionKey sk) {
        //取到关联的channle
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) sk.channel();
            //创建buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            //根据count的值做处理
            if (count > 0) {
                //把缓存区的数据转成字符串
                String msg = new String(buffer.array());
                //输出该消息
                System.out.println("form 客户端: " + msg);
                //向其它的客户端转发消息(去掉自己), 专门写一个方法来处理
                sendInfoToOtherClients(msg, channel);
            }
        } catch (Exception e) {
            try {
                System.out.println(channel.getRemoteAddress() + " 离线了..");
                e.printStackTrace();
                //取消注册
                sk.cancel();
                //关闭通道
                channel.close();
            } catch (IOException e2) {
                e2.printStackTrace();
                ;
            }
        }


    }

    private void sendInfoToOtherClients(String msg, SocketChannel channel) throws IOException {
        System.out.println("服务器转发消息中...");
        System.out.println("服务器转发数据给客户端线程: " + Thread.currentThread().getName());
        //遍历 所有注册到selector 上的 SocketChannel,并排除 self
        for (SelectionKey key : selector.keys()) {
            //通过 key  取出对应的 SocketChannel
            Channel targetChannel = key.channel();
            //排除自己
            if (targetChannel instanceof SocketChannel && targetChannel != channel) {
                //转型
                SocketChannel dest = (SocketChannel) targetChannel;
                //将msg 存储到buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                //将buffer 的数据写入 通道
                dest.write(buffer);
            }
        }

    }


}
