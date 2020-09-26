package com.i;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/26 3:33 下午
 */
@Component("testBean2")
@Scope("singleton")
public  abstract  class TestBean {


    @Lookup
    public abstract  AnotherBean anotherBean();


    public void pringtAnotherBean() {
        System.out.println(anotherBean());
    }



}
