package org.clxmm.springboot07mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "org.clxmm.springboot07mybatis.mapper")
@SpringBootApplication
public class SpringBoot07MybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot07MybatisApplication.class, args);
	}

}
