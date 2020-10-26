package org.clmmm.springboot010cach.service;

import org.clmmm.springboot010cach.bean.Department;
import org.clmmm.springboot010cach.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/22 9:11 下午
 */
@Service
public class DepartMentService {


    @Autowired
    DepartmentMapper departmentMapper;



    @Cacheable(cacheNames = "dept")
    public Department getDeptById(Integer id) {
        System.out.println(" 查询部门 " + id);
        Department department = departmentMapper.getDeptById(id);
        return department;
    }



}
