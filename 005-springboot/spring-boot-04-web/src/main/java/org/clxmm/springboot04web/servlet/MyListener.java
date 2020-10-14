package org.clxmm.springboot04web.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/11 9:05 下午
 */
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(" web 应用器启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("web 当前web项目销毁");
    }
}
