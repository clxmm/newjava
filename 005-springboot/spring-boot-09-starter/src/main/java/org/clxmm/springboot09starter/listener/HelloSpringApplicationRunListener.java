package org.clxmm.springboot09starter.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/15 8:27 下午
 */
public class HelloSpringApplicationRunListener implements SpringApplicationRunListener {


    public HelloSpringApplicationRunListener (SpringApplication application, String[] args) {


    }


    @Override
    public void starting() {
        System.out.println("SpringApplicationRunListener starting");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("SpringApplicationRunListener environmentPrepared" + environment );
        System.out.println(environment.getSystemProperties().get("os.name"));
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener contextPrepared " +context);
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {

        System.out.println("SpringApplicationRunListener  contextLoaded");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener  started");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener   running");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("ConfigurableApplicationContext failed");
    }
}
