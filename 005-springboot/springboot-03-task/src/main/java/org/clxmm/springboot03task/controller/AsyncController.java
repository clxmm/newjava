package org.clxmm.springboot03task.controller;

import org.clxmm.springboot03task.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/31 4:37 下午
 */
@RestController
public class AsyncController {



    @Autowired
    AsyncService asyncService;


    @GetMapping("hello")
    public String hello() {


        String hello = asyncService.hello();
        System.out.println(hello);
        return hello;
    }




}
