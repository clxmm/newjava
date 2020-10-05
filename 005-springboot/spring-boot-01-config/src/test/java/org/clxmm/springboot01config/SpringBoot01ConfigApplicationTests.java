package org.clxmm.springboot01config;

import org.clxmm.springboot01config.bean.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class SpringBoot01ConfigApplicationTests {


	@Autowired
	Person person;

	@Autowired
	ApplicationContext context;

	@Test
	void contextLoads() {

		System.out.println(person);

		System.out.println(context.containsBean("helloService"));

	}

}
