package org.clmmm.springboot010cach.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.clmmm.springboot010cach.bean.Employee;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/17 5:16 下午
 */
public interface EmployeeMapper {

    @Select("select * from employee where id =#{id}")
    public Employee getEmpById(Integer id);


    @Update("update employee set lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} where id=#{id} ")
    public void updateEmployee (Employee employee);


    @Delete("delete from employee where id=#{id}")
    public void deleteEmpById(Integer id);

    @Insert("insert into(lastName,email,gender,d_id) values(#{lastName},#{email},#{gender},#{dId})")
    public void insertEmp(Employee employee);

    @Select("select * from employee where lastName=#{lastName} ")
    Employee getEmpByLastName(String lastName);
}
