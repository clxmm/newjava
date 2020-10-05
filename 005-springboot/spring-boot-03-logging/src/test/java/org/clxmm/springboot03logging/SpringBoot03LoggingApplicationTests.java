package org.clxmm.springboot03logging;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBoot03LoggingApplicationTests {

	// 记录器
	Logger logger = LoggerFactory.getLogger(getClass());
	@Test
	void contextLoads() {

		// 日志的级别 由低到高  trace 《debug 《info 《error
		// 调整日志的输出的日志级别；
		logger.trace("this is");
		logger.debug("debug info ");

		// 默认是info级别的
		logger.info("info");
		logger.warn("warn message");
		logger.error("error message");


	}

}
