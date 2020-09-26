package com.i;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/26 3:34 下午
 */

@Configuration
@ComponentScan
public class TestConfigure {


/*
    @Bean("testBean1")
    @Scope("myScope")
    public TestBean testBean() {
        return new TestBean();
    }*/


    @Bean("myScope")
    public MyScope myScope() {
        return new MyScope();
    }


    // 自定义作用域
    @Bean
    public CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
        customScopeConfigurer.addScope("myScope",myScope());

        return customScopeConfigurer;
    }


}
