package com.i
        ;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/25 8:42 下午
 */
@Configuration
@ComponentScan
public class MyConfig {

    @Bean
    public List<String> stringList() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        return list;
    }

    // 优先级高于上面
    @Bean
    @Order(56)
    public String string1() {
        return "11";
    }

    @Bean
    @Order(28)  // 调整顺序
    public String string2() {
        return "22";
    }


    @Bean
    public Map<String,Integer> integerMap() {
        Map<String,Integer> map = new HashMap<>();
        map.put("a",1);
        return map;
    }
    @Bean
    public Integer integer1() {
        return 11;
    }

    @Bean
    public Integer integer2() {
        return 12;
    }





}
