package org.clxmm.service.edu.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenFeign日志
 * @author clxmm
 * @version 1.0
 * @date 2020/11/21 10:35 下午
 */
@Configuration
public class OpenFeignConfig {


    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


}
