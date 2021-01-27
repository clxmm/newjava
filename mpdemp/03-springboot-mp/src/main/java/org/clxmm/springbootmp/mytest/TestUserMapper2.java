package org.clxmm.springbootmp.mytest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.clxmm.springbootmp.mapper.UserMapper;
import org.clxmm.springbootmp.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/21 8:27 下午
 */
@SpringBootTest
public class TestUserMapper2 {


    // SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE id=?
    @Test
    public void selectTest() {
        User user = new User();
        user.setId(1L);
        User user1 = user.selectById();
        System.out.println(user);
        System.out.println(user1);
    }


    // INSERT INTO tb_user ( user_name, password, name, age, email ) VALUES ( ?, ?, ?, ?, ? )
    @Test
    public void testInsert() {

        User user = new User();
//        user.setId(1L);
        user.setName("clxmm");
        user.setAge(10);
        user.setPassword("123456");
        user.setUserName("liubei");
        user.setMail("liubei@itcast.cn");
        boolean insert = user.insert();
        System.out.println(insert);

    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(1L);
        user.setAge(20);

        boolean b = user.updateById();
        System.out.println(b);
    }


    @Test
    public void testDelete() {
        User user = new User();
        user.setId(8L);
        boolean b = user.deleteById();
        System.out.println(b);
    }


    // SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE age <= ?
    @Test
    public void testQuery() {
        User user = new User();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.le("age", "20");
        List<User> users = user.selectList(userQueryWrapper);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }




}
