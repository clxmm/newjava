package org.clxmm.springboot04web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/6 8:12 下午
 */
@Controller
public class LoginController {



    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session) {
        if(!StringUtils.isEmpty(username) && "123456".equals(password)) {
            session.setAttribute("loginUser",username);
            //            重定向
            return  "redirect:/main.html";
        } else {
            // 登陆失败
            map.put("msg","用户名或密码错误");
            return  "login";
        }



    }



}
