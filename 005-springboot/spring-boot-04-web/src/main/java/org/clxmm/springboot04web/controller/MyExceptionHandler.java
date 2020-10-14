package org.clxmm.springboot04web.controller;

import org.clxmm.springboot04web.exception.UserNotException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/11 4:02 下午
 */
@ControllerAdvice
public class MyExceptionHandler {


    //1， 浏览器和客户端返回的都是json
//    @ResponseBody
//    @ExceptionHandler(UserNotException.class)
//    public Map handleException(Exception e) {
//        Map<String,Object> map = new HashMap<>();
//        map.put("code","user.notex");
//        map.put("msg",e.getMessage());
//
//        return map;
//
//    }

    @ExceptionHandler(UserNotException.class)
    public String handleException(Exception e, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        map.put("code","user.notex");
        map.put("msg","用户不存在");

        // 传入自己的状态吗
        request.setAttribute("javax.servlet.error.status_code",400);

        request.setAttribute("ext",map);
        return "forward:/error";

    }






}
