package org.clxmm.springboot07mybatis.bean;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/13 8:45 下午
 */
public class Department {
    private Integer id;
    private String departmentName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
