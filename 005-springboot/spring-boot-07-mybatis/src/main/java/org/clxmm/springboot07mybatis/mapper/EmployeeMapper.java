package org.clxmm.springboot07mybatis.mapper;

import org.clxmm.springboot07mybatis.bean.Employee;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/14 7:08 下午
 */
// @mapper @mapperScan 将接口扫描装配到容器中
public interface EmployeeMapper {


    public Employee getEmpById(Integer id);


    public void insertEmp(Employee employee);



}
