package org.clxmm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/17 1:47 下午
 */
public class Log4j2Test {


    public static final Logger LOGGER = LogManager.getLogger(Log4j2Test.class);

    @Test
    public void test1() {
        LOGGER.fatal("fatal ");
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");
    }

}
