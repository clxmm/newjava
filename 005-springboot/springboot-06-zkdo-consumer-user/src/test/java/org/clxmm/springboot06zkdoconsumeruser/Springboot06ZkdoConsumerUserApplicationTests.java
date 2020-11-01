package org.clxmm.springboot06zkdoconsumeruser;

import org.clxmm.springboot06zkdoconsumeruser.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot06ZkdoConsumerUserApplicationTests {

	@Autowired
	UserService userService;
	@Test
	void contextLoads() {

		userService.hello();
	}

}
