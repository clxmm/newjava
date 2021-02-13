package org.clxmm.service.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/2/13 1:28 下午
 */
@SpringBootApplication
@ComponentScan({"org.clxmm"})
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceTradeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceTradeApplication.class);
    }
}
