package org.clxmm.service.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/9 9:49 下午
 */
@SpringBootApplication
@EnableFeignClients
@ComponentScan({"org.clxmm"})
public class ServiceCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCmsApplication.class,args);
    }


}
