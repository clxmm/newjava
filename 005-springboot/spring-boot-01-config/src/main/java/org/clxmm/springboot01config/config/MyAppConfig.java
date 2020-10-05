package org.clxmm.springboot01config.config;

import org.clxmm.springboot01config.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/2 3:12 下午
 */
@Configuration   // 指名当前类是一个配置类，就是来替代之前的配置文件
public class MyAppConfig {




    // 将方法的返回值添加容器中，组件的默认id就是方法名
    @Bean
    public HelloService helloService() {
        System.out.println("-----");
        return new HelloService();
    }

}
