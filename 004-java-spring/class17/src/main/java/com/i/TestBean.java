package com.i;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/26 4:04 下午
 */
//@Component
public class TestBean implements InitializingBean, DisposableBean {

    public void destroy() throws Exception {
        System.out.println("destroy");
    }

    public void afterPropertiesSet() throws Exception {

        System.out.println(" init");
    }


    //        第二种方法//
    @PostConstruct
    public void onInit() {
        System.out.println("init on");
    }

    @PreDestroy
    public void onDestory() {
        System.out.println("on destory");
    }


    // 第三种


    public void onInit1() {
        System.out.println("init1 on");
    }

    @PreDestroy
    public void onDestory1() {
        System.out.println("on destory1");
    }


}
