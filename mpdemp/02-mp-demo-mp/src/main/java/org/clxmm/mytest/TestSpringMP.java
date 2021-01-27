package org.clxmm.mytest;

import org.clxmm.mapper.UserMapper;
import org.clxmm.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/21 7:50 下午
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestSpringMP {

    @Autowired
    UserMapper userMapper;



    @Test
    public void test() {
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }



}
