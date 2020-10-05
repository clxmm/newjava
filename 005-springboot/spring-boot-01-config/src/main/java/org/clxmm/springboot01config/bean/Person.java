package org.clxmm.springboot01config.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 将配置文件中配置的值映射到这个组件
 * @ConfigurationProperties 将本类中的所有属性和配置文件中相关的配置进行绑定，
 *  prefix = "person" ： 配置文件中哪个下面的所有属性进行一一映射
 *  只有这个组件是容器中的组件，才能使用容器提供的功能
 *
 * @author clxmm
 * @version 1.0
 * @date 2020/10/2 1:22 下午
 */
@Component
@ConfigurationProperties(prefix = "person")
//@Validated
//@PropertySource(value = {"classpath:person.properties"})

public class Person {


    /**
     *  类似与bean标签
     *
     *
     */
//    @Value("${person.name}")
//    @Emali

    private String name;

//    @Value("#{11*2}")
    private Integer age;

//    @Value("true")
    private Boolean boss;

    private Date birth;


    private Map<String,Object> maps;



    private List<Object> lists;

    private Dog dog;


    @Override
    public String  toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", maps=" + maps +
                ", lists=" + lists +
                ", dog=" + dog +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
}
