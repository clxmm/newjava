package org.clxmm.springboot07mybatis.config;


import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/13 9:05 下午
 */
@org.springframework.context.annotation.Configuration
public class MyBatisConfig {

    @Bean
    public ConfigurationCustomizer customizer() {
        return new ConfigurationCustomizer() {

            @Override
            public void customize(Configuration configuration) {
                // 开启驼峰命名
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }


}
