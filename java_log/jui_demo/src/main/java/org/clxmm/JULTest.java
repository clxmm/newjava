package org.clxmm;

import org.junit.Test;
import sun.java2d.jules.JulesTile;

import java.io.InputStream;
import java.util.logging.*;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/10 8:20 下午
 */
public class JULTest {

    @Test
    public void test1() {
        //  获取日志对象
        Logger logger = Logger.getLogger("org.clxmm.JULTest");
        // 2。 日志记录输出
        logger.info("hello jul");

        //  3。通用方法输出log
        logger.log(Level.INFO, "inf msg");

        // 4，占位符输出
        String name = "clx";
        String age = "10";

        logger.log(Level.INFO, "用户信息： {0},{1}", new Object[]{name, age});
    }

    // 默认只实现info以上的级别
    @Test
    public void test02LogLevel() throws Exception {
        Logger logger = Logger.getLogger("org.clxmm.JULTest");
        // 2.日志记录输出
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("cofnig");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    // 自定义日志级别
    @Test
    public void test02LogLevelConfig() throws Exception {
        Logger logger = Logger.getLogger("org.clxmm.JULTest");
        //      自定义日志级别

        // 关闭默认的配置
        logger.setUseParentHandlers(false);

        // 创建ConsoleHandler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        // 格式转换  （默认的SimpleFormatter）
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        // 进行关联
        consoleHandler.setFormatter(simpleFormatter);
        logger.addHandler(consoleHandler);

        // 自定义级别
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);


        // 二、输出到日志文件
        FileHandler fileHandler = new FileHandler("d:/logs/jul.log");
        fileHandler.setFormatter(simpleFormatter);
        logger.addHandler(fileHandler);

        // 2.日志记录输出
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("cofnig");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    @Test
    public void testLogParent() throws Exception {
        Logger logger1 = Logger.getLogger("org.clxmm");
        Logger logger2 = Logger.getLogger("org");

        System.out.println((logger1.getParent()) == logger2);

        //所有日志记录器对象的顶级父元素 class为java.util.logging.LogManager$RootLogger name为""
        String s = logger2.getParent() + "name:" + logger2.getParent().getName() +";";
        System.out.println(s); // java.util.logging.LogManager$RootLogger@3581c5f3name:;

        logger2.setUseParentHandlers(false);

        // 创建ConsoleHandler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        // 格式转换  （默认的SimpleFormatter）
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        // 进行关联
        consoleHandler.setFormatter(simpleFormatter);
        logger2.addHandler(consoleHandler);

        // 自定义级别
        logger2.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);


        // logger1 的输出回继承logger2 的日志输出级别
        logger1.severe("severe");
        logger1.warning("warning");
        logger1.info("info");
        logger1.config("cofnig");
        logger1.fine("fine");
        logger1.finer("finer");
        logger1.finest("finest");

    }

    // 加载自定义的配置文件
    @Test
    public void TestLogProperties() throws  Exception {
        // 读取配置文件
        // 创建logManager
//        通过logManager加载配置文件
        InputStream inputStream = JULTest.class.getClassLoader().getResourceAsStream("logging.properties");
        LogManager logManager = LogManager.getLogManager();
        logManager.readConfiguration(inputStream);

        Logger logger = Logger.getLogger("org.clxmm");
        // 2.日志记录输出
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("cofnig");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");


        System.out.println("----");
        Logger logger2 = Logger.getLogger("org.clxmmTest");
        // 2.日志记录输出
        logger2.severe("severe test");
        logger2.warning("warning test");
        logger2.info("info test");
        logger2.config("cofnig test");
        logger2.fine("fine test");
        logger2.finer("finer test");
        logger2.finest("finest tes");


    }

}
