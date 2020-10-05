package com.example.springboot01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringBoot01Application {

	public static void main(String[] args) {
		// spring应用启动起来
		SpringApplication.run(SpringBoot01Application.class, args);
	}

	@GetMapping("hello")
	public String hello() {
		return "hello";
	}


}
