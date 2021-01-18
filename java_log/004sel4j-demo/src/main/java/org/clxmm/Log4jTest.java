package org.clxmm;

import org.apache.log4j.Logger;
import org.junit.Test;


/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/16 7:52 下午
 */
public class Log4jTest {

    public static final Logger LOGGER = Logger.getLogger(Log4jTest.class);

    // 测试桥接器
    @Test
    public void  test() {
        LOGGER.info("sel4j test");
    }

}
