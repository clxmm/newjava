package org.clxmm.springboot04web.config;

import org.clxmm.springboot04web.servlet.MyFilter;
import org.clxmm.springboot04web.servlet.MyListener;
import org.clxmm.springboot04web.servlet.MyServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Arrays;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/11 8:52 下午
 */
@Configuration
public class MyServerConfig {

    //
    @Bean
    public ServletRegistrationBean myServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new MyServlet(),"/myServlet");

        return registrationBean;
    }



    @Bean
    public FilterRegistrationBean MyFilter() {

        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new MyFilter());
        bean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));

        return bean;
    }


    @Bean
    public ServletListenerRegistrationBean myListener() {
        ServletListenerRegistrationBean<MyListener> bean = new ServletListenerRegistrationBean<>(new MyListener()
        );

        return bean;

    }



}
