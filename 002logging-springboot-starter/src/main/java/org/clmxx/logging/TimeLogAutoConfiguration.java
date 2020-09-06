package org.clmxx.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author clx
 * @date 2020-09-06 17:58
 */
@Configuration
@Aspect
@EnableAspectJAutoProxy
@ConditionalOnProperty(prefix = "clxmm.time", name = "enable", havingValue = "true", matchIfMissing = true)
public class TimeLogAutoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeLogAutoConfiguration.class);

    @Around("@annotation(org.clmxx.logging.TimeLog)")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        // pubbil java.test org.TestHello
        String name = point.getSignature().toLongString().split(" ")[2];

        long start = System.currentTimeMillis();
        Object object = point.proceed();
        long end = System.currentTimeMillis();
        LOGGER.info("方法 {} 耗时 {} ms",name,(end-start) );
        return object;
    }

}
