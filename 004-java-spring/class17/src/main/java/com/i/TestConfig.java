package com.i;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/26 4:04 下午
 */
@ComponentScan
@Configuration
public class TestConfig {

    @Bean(initMethod = "onInit1",destroyMethod = "onDestory1")   // @√ΩComponent  没有这两个属性
    public TestBean testBean() {
        return new TestBean();
    }






}
