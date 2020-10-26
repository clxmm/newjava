package org.clmmm.springboot010cach.mapper;

import org.apache.ibatis.annotations.Select;
import org.clmmm.springboot010cach.bean.Department;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/17 5:16 下午
 */
public interface DepartmentMapper {

    @Select("select * from department where id = #{id}")
    Department getDeptById(Integer id);
}
