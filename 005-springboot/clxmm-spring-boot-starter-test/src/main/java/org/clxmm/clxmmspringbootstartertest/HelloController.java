package org.clxmm.clxmmspringbootstartertest;

import org.clxmm.clxmmspringbootstarterautoconfigura.starter.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/17 4:23 下午
 */
@RestController
public class HelloController {


    @Autowired
    HelloService helloService;

    @GetMapping("/test")
    public String test() {
        return helloService.sayHello("clxmm");
    }


}
