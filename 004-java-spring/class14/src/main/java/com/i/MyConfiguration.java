package com.i;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/25 8:12 下午
 */
@Configuration  // 创建一个class配置文件
@ComponentScan(value = "com.i")
public class MyConfiguration {



//    @Bean(value = "bean11")  // 配置bean
//    public Bean1 bean1() {
//        return new Bean1();
//    }


}
