package org.clxmm.springboot04web.controller;

import org.clxmm.springboot04web.exception.UserNotException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/4 9:06 下午
 */
@RestController
@RequestMapping
public class HelloController {


    @GetMapping("/hello")
    public String hello(String user) {

        if(user.equals("aaa")) {
            throw new UserNotException();
        }
        return "hello;";
    }


}
