package com.i;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/26 3:55 下午
 */
@Configuration
@ComponentScan
@Lazy  // 全局
public class TestConfig {


    @Bean("testBean2")
//    @Lazy
    public TestBean testBean() {
        return new TestBean();
    }


}
