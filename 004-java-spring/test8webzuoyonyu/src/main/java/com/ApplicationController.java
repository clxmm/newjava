package com;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/20 4:58 下午
 */
@Controller
public class ApplicationController {


    @RequestMapping("testApp")
    @ResponseBody
    public String test() {
        return this.toString();
    }
}
