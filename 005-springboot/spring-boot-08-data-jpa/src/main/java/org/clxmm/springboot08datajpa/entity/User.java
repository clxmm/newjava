package org.clxmm.springboot08datajpa.entity;

import javax.persistence.*;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/14 7:58 下午
 */
// 使用jpa 注解配置映射关系
// 告诉jap 这是一个实体类 （和数据表映射的类）

@Entity
@Table(name = "ta1_user")   // 和哪个数据表对应； 省略默认是类名小写
public class User {
    @Id  //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 自增主键
    private Integer id;

    @Column(name = "last_name",length = 50)
    private String lastName;

    @Column // 省略，列名就是属性名
    private String email;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
