package org.clxmm.springboot07mybatis.mapper;

import org.apache.ibatis.annotations.*;
import org.clxmm.springboot07mybatis.bean.Department;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/13 8:47 下午
 */
//@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id} ")
    public Department getDepById(Integer id);

    @Delete("delete from department where id = #{id}")
    public int deleteDeptById();

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department (department_name) values(#{departmentName}) ")
    public int insertDept(Department department);

    @Update("update department set department_name=#{departmentName} where id = #{id}")
    public int updateDept(Department department);
}
