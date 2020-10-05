package org.clxmm.springboot01helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/1 7:27 下午
 */
@RestController
public class HelloController {


    @GetMapping("/hello")
    public String hello() {
        return "hello 111";
    }

}
