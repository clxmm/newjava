package com.i.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/25 8:40 下午
 */
@Component
public class MyBean {


    private AnotherBean anotherBean1;
    private AnotherBean anotherBean2;


    @Autowired
    private AnotherBean anotherBean3;


    private List<String> stringList;

    public List<String> getStringList() {
        return stringList;
    }

    @Autowired
    @Qualifier("stringList")  //指定bean的id
    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }





    //map类型
    private Map<String,Integer> integerMap;


    // 简单类型
    public String string;


    // 内置接口类
    public ApplicationContext context;


    public ApplicationContext getContext() {
        return context;
    }

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public String getString() {
        return string;
    }

    @Value("111")
    public void setString(String string) {
        this.string = string;
    }

    public Map<String, Integer> getIntegerMap() {
        return integerMap;
    }

    @Autowired
    public void setIntegerMap(Map<String, Integer> integerMap) {
        this.integerMap = integerMap;
    }

    // 构造方法注入bean
    @Autowired
    public MyBean(AnotherBean anotherBean1) {
        this.anotherBean1 = anotherBean1;
    }

    // set 方法注入bean
    @Autowired
    public void setAnotherBean2(AnotherBean anotherBean2) {
        this.anotherBean2 = anotherBean2;
    }


    @Override
    public String toString() {
        return "MyBean{" +
                "anotherBean1=" + anotherBean1 +
                ", anotherBean2=" + anotherBean2 +
                ", anotherBean3=" + anotherBean3 +
                ", string=" + string +
                '}';
    }
}
