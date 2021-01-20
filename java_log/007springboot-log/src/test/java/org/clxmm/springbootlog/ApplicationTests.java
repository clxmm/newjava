package org.clxmm.springbootlog;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {


    public static final Logger logger = LoggerFactory.getLogger(ApplicationTests.class);

    @Test
    void contextLoads() {

        // 打印日志信息
        logger.error("error");
        logger.warn("warn");
        logger.info("info");
        // 默认日志级别
        logger.debug("debug");
        logger.trace("trace");


        // log4j 使用桥接器切换为slf4j门面和logback 日志实现
        org.apache.logging.log4j.Logger logger1 = LogManager.getLogger(ApplicationTests.class);
        logger1.info("info");

    }

}
