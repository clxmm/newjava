package org.clmmm.springboot010cach.controller;

import org.clmmm.springboot010cach.bean.Department;
import org.clmmm.springboot010cach.service.DepartMentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/22 9:11 下午
 */
@RestController
public class DepartController {

    @Autowired
    DepartMentService departMentService;

    @GetMapping("/dept/{id}")
    public Department getDeptById(@PathVariable("id") Integer id) {

        return departMentService.getDeptById(id);
    }

}
