package org.clxmm.springboot08datajpa.controller;

import org.clxmm.springboot08datajpa.entity.User;
import org.clxmm.springboot08datajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/14 8:13 下午
 */
@RestController
public class UserController {


    @Autowired
    UserRepository repository;


    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        User user = repository.findById(id).get();
        return user;
    }


    @GetMapping("/addUser")
    public void insertAddUser() {
        User user = new User();
        user.setLastName("test");
        user.setEmail("112@qq.com");

        repository.save(user);
    }

}

