package org.clxmm;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;
import org.junit.Test;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/12 8:09 下午
 */
public class Log4jTest {

    @Test
    public void test1() {
        /**
         * log4j:WARN No appenders could be found for logger (org.clxmm.Log4jTest).
         * log4j:WARN Please initialize the log4j system properly.
         * log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
         */
        // 开启 log4j 的内部日志1记录
        LogLog.setInternalDebugging(true);
        // 初始化配置信息
//        BasicConfigurator.configure();
        // 获取日志记录器对象
        Logger logger = Logger.getLogger(Log4jTest.class);

        //日志记录的输出
        logger.info("hello");
        // 0 [main] INFO org.clxmm.Log4jTest  - hello

        logger.fatal("fatal");   // 严重错误，

        logger.error("error");     // 错误信息
        logger.warn("warn");
        logger.info("info");
        logger.debug("debug");  // 调试信息，一般在开发阶段使用，记录程序的变量、参数等

        logger.trace("trace");  // 追踪信息，记录程序的所有流程信息
    }
}
