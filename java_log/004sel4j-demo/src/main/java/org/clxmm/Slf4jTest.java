package org.clxmm;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/16 6:53 下午
 */
public class Slf4jTest {
    // 声明日志对象
    public static final Logger LOGGER = LoggerFactory.getLogger(Slf4jTest.class);

    @Test
    public void test01 () throws Exception {
        // 日志输出
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");

        // 使用占位符输出日志信息
        String name = "clx";
        Integer age = 18;
        LOGGER.info("name: {} age: {}",name,age);

        // 系统的异常信息
        try {
            int i= 1/0;
        } catch (Exception e) {
            LOGGER.warn( "error",e);
        }

    }
}
