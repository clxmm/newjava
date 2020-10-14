package org.clxmm.springboot07mybatis.controller;

import org.clxmm.springboot07mybatis.bean.Department;
import org.clxmm.springboot07mybatis.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/13 8:53 下午
 */
@RestController
public class DeptController {

    @Autowired
    DepartmentMapper departmentMapper;




    @GetMapping("/dept/{id}")
    public Department getDeptById(@PathVariable("id") Integer id) {
        return departmentMapper.getDepById(id);
    }


    @GetMapping("/dept")
    public Department insertDept(Department department) {
        departmentMapper.insertDept(department);
        return department;
    }
}
