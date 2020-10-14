package org.clxmm.springboot07mybatis.controller;

import org.clxmm.springboot07mybatis.bean.Employee;
import org.clxmm.springboot07mybatis.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/14 7:30 下午
 */
@RestController
public class EmployeeController {
    @Autowired
    EmployeeMapper employeeMapper;


    @GetMapping("/emp")
    public Employee getEmployeeById(Integer id) {

        return employeeMapper.getEmpById(id);
    }

    @GetMapping("/empAdsd")
    public void insertEmployee() {


        Employee employee = new Employee();
        employee.setLastName("tes");
        employee.setEmail("1111@qq.com");
        employee.setGender(1);
        employee.setdId(1);
        employeeMapper.insertEmp(employee);


    }


}
