package org.clxmm.springboot03task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync    // 开启异步注解
@EnableScheduling // 开启定时任务
public class Springboot03TaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot03TaskApplication.class, args);
	}

}
