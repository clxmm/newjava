package org.clxmm.springboot01config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//@ImportResource(locations = {"classpath:beans.xml"})
public class SpringBoot01ConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot01ConfigApplication.class, args);
	}

}
