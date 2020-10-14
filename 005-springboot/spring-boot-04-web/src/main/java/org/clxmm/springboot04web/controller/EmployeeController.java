package org.clxmm.springboot04web.controller;

import org.clxmm.springboot04web.dao.DepartmentDao;
import org.clxmm.springboot04web.dao.EmployeeDao;
import org.clxmm.springboot04web.entities.Department;
import org.clxmm.springboot04web.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/7 8:33 下午
 */
@Controller
public class EmployeeController {



    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;


    // 查询1所有员工，
    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }


    // 员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model) {

        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }
    //员工添加
    // springmvc 自动将请求参数和入参对象的属性一一绑定，参数名和javabean里面的属性名一样
    @PostMapping("/emp")
    public String addEmp(Employee employee) {

        employeeDao.save(employee);
        System.out.println(employee);
        //来到员工列表页面
        // redirect 重定向
//        forward
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model) {
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);

        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);

        return "emp/add";
    }


    @PutMapping("/emp")
    public String updateEmp(Employee employee) {

        employeeDao.save(employee);

        return "redirect:/emps";
    }

    // 员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id) {

        employeeDao.delete(id);
        return "redirect:/emps";
    }

}
