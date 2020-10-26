package org.clmmm.springboot010cach.controller;

import org.clmmm.springboot010cach.bean.Employee;
import org.clmmm.springboot010cach.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.DocFlavor;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/17 5:26 下午
 */
@RestController
@RequestMapping
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id) {
        return employeeService.getEmp(id);
    }


    @GetMapping("/emp/update")
    public Employee update(Integer id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setLastName("test111");
        employee.setEmail("111@qq.com111");
        employee.setGender(1);
        return employeeService.updateEmp(employee);
    }


    @GetMapping("/delete")
    public String deleteEmp(Integer id) {
        employeeService.deleteEmp(id);

        return " " + id;
    }

    @GetMapping("/getLastName")
    public Employee getLastName(String lastName) {
        return employeeService.getEmpByLastName(lastName);
    }



}
