package org.clxmm.bio07all.clint;

import org.clxmm.bio07all.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/3/7 5:08 下午
 */
public class ClientChat implements ActionListener {
    /**
     * 2.消息内容框架
     */
    public JTextArea smsContent = new JTextArea(23, 50);
    /**
     * 展示在线人数的窗口
     */
    public JList<String> onLineUsers = new JList<>();
    /**
     * 1.设计界面
     */
    private JFrame win = new JFrame();
    /** 4.在线人数的区域  */
    /** 存放人的数据 */
    /**
     * 3.发送消息的框
     */
    private JTextArea smsSend = new JTextArea(4, 40);
    // 是否私聊按钮
    private JCheckBox isPrivateBn = new JCheckBox("私聊");
    // 消息按钮
    private JButton sendBn = new JButton("发送");

    // 登录界面
    private JFrame loginView;

    private JTextField ipEt, nameEt, idEt;

    private Socket socket;

    public static void main(String[] args) {
        new ClientChat().initView();

    }

    private static void setWindowCenter(JFrame frame, int width, int height, boolean flag) {
        /** 得到所在系统所在屏幕的宽高 */
        Dimension ds = frame.getToolkit().getScreenSize();

        /** 拿到电脑的宽 */
        int width1 = ds.width;
        /** 高 */
        int height1 = ds.height;

        System.out.println(width1 + "*" + height1);
        /** 设置窗口的左上角坐标 */
        frame.setLocation(width1 / 2 - width / 2, height1 / 2 - height / 2);
        frame.setVisible(flag);
    }

    private void initView() {
        /** 初始化聊天窗口的界面 */
        win.setSize(650, 600);

        /** 展示登录界面  */
        displayLoginView();

        /** 展示聊天界面 */
        //displayChatView();

    }

    private void displayChatView() {

        JPanel bottomPanel = new JPanel(new BorderLayout());
        //-----------------------------------------------
        // 将消息框和按钮 添加到窗口的底端
        win.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(smsSend);
        JPanel btns = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btns.add(sendBn);
        btns.add(isPrivateBn);
        bottomPanel.add(btns, BorderLayout.EAST);
        //-----------------------------------------------
        // 给发送消息按钮绑定点击事件监听器
        // 将展示消息区centerPanel添加到窗口的中间
        smsContent.setBackground(new Color(0xdd, 0xdd, 0xdd));
        // 让展示消息区可以滚动。
        win.add(new JScrollPane(smsContent), BorderLayout.CENTER);
        smsContent.setEditable(false);
        //-----------------------------------------------
        // 用户列表和是否私聊放到窗口的最右边
        Box rightBox = new Box(BoxLayout.Y_AXIS);
        onLineUsers.setFixedCellWidth(120);
        onLineUsers.setVisibleRowCount(13);
        rightBox.add(new JScrollPane(onLineUsers));
        win.add(rightBox, BorderLayout.EAST);
        //-----------------------------------------------
        // 关闭窗口退出当前程序
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.pack();  // swing 加上这句 就可以拥有关闭窗口的功能
        /** 设置窗口居中,显示出来  */
        setWindowCenter(win, 650, 600, true);
        // 发送按钮绑定点击事件
        sendBn.addActionListener(this);
    }

    private void displayLoginView() {

        /** 先让用户进行登录
         *  服务端ip
         *  用户名
         *  id
         *  */
        /** 显示一个qq的登录框     */
        loginView = new JFrame("登录");
        loginView.setLayout(new GridLayout(3, 1));
        loginView.setSize(400, 230);

        JPanel ip = new JPanel();
        JLabel label = new JLabel("   IP:");
        ip.add(label);
        ipEt = new JTextField(20);
        ip.add(ipEt);
        loginView.add(ip);

        JPanel name = new JPanel();
        JLabel label1 = new JLabel("姓名:");
        name.add(label1);
        nameEt = new JTextField(20);
        name.add(nameEt);
        loginView.add(name);

        JPanel btnView = new JPanel();
        JButton login = new JButton("登陆");
        btnView.add(login);
        JButton cancle = new JButton("取消");
        btnView.add(cancle);
        loginView.add(btnView);
        // 关闭窗口退出当前程序
        loginView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setWindowCenter(loginView, 400, 260, true);

        /** 给登录和取消绑定点击事件 */
        login.addActionListener(this);
        cancle.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
/** 得到点击的事件源 */
        JButton btn = (JButton) e.getSource();
        switch (btn.getText()) {
            case "登陆":
                String ip = ipEt.getText().toString();
                String name = nameEt.getText().toString();
                // 校验参数是否为空
                // 错误提示
                String msg = "";
                // 12.1.2.0
                // \d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}\
                if (ip == null || !ip.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
                    msg = "请输入合法的服务端ip地址";
                } else if (name == null || !name.matches("\\S{1,}")) {
                    msg = "姓名必须1个字符以上";
                }

                if (!msg.equals("")) {
                    /** msg有内容说明参数有为空 */
                    // 参数一：弹出放到哪个窗口里面
                    JOptionPane.showMessageDialog(loginView, msg);
                } else {
                    try {
                        // 参数都合法了
                        // 当前登录的用户,去服务端登陆
                        /** 先把当前用户的名称展示到界面 */
                        win.setTitle(name);
                        // 去服务端登陆连接一个socket管道
                        socket = new Socket(ip, Constants.PORT);

                        //为客户端的socket分配一个线程 专门负责收消息
                        new ClientReader(this, socket).start();

                        // 带上用户信息过去
                        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                        dos.writeInt(1); // 登录消息
                        dos.writeUTF(name.trim());
                        dos.flush();

                        // 关系当前窗口 弹出聊天界面
                        loginView.dispose(); // 登录窗口销毁
                        displayChatView(); // 展示了聊天窗口了


                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                break;
            case "取消":
                /** 退出系统 */
                System.exit(0);
                break;
            case "发送":

                break;

        }


    }
}
