package org.clxmm.service.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/11/16 9:22 下午
 */
@SpringBootApplication
(exclude = DataSourceAutoConfiguration.class)//取消数据源自动配置
@ComponentScan({"org.clxmm"})
@EnableDiscoveryClient
public class ServiceOssApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOssApplication.class,args);
    }


}
