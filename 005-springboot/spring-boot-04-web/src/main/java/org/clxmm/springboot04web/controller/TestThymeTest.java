package org.clxmm.springboot04web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.Map;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/5 8:57 下午
 */
@Controller
public class TestThymeTest {


    // 查出一些数据在页面展示
    @GetMapping("success")
    public String success(Map<String,Object> map) {

        // classpath:/templates/success.html
        map.put("hello","<h1>你好阿</h1>");
        map.put("users", Arrays.asList("jack","tom"));
        return "success";
    }




}
