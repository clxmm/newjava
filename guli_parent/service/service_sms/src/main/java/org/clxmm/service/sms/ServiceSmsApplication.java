package org.clxmm.service.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/2/2 7:49 下午
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"org.clxmm"})
@EnableDiscoveryClient
public class ServiceSmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceSmsApplication.class);
    }

}
