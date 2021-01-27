package org.clxmm.springbootmp.mytest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.org.apache.xalan.internal.xsltc.dom.SortingIterator;
import org.clxmm.springbootmp.enums.SexEnum;
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
public class TestUserMapper {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testInsert() {


        User user = new User();
        user.setName("clx1111女");
        user.setAge(1231);
        user.setMail("clxmm@.com");
        user.setUserName("123");
        user.setSex(SexEnum.WOMAN);
        int insert = userMapper.insert(user); // 数据库受影响的行数
        System.out.println(insert);

        // h获取自增长的ID ，回填到user 对象中
        System.out.println(user.getId());
    }


    // 根据id去做更新
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(1L);
        user.setName("test");
        int i = userMapper.updateById(user);
        System.out.println(user);
    }

    // 利用查询条件更新
    @Test
    public void testUpdate1() {
        User user = new User();
        user.setAge(10);
        user.setName("test");
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, "lisi");
        int update = userMapper.update(user, queryWrapper);
        System.out.println(update);
    }

    // 根据跟新条件更新
    @Test
    public void testUpdate2() {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.set("age", "12").eq("user_name", "lisi");
        int update = userMapper.update(null, userUpdateWrapper);
        System.out.println(update);
    }


    //根据id删除 / 批量删除
    // DELETE FROM tb_user WHERE id=?
    // DELETE FROM tb_user WHERE id IN ( ? , ? , ? )
    @Test
    public void testDelete1() {
        int i = userMapper.deleteById("7");
        System.out.println(i);


        int i1 = userMapper.deleteBatchIds(Arrays.asList("10", "11", "13"));
        System.out.println(i1);

    }

    //将columnMap中的元素设置为删除的条件，多个之间为and关系
    @Test
    public void testDelete2() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "clx");
        map.put("age", 20);
        int i = userMapper.deleteByMap(map);
        System.out.println(i);
    }

    @Test
    public void testDelete3() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, "lisi");
        userMapper.delete(queryWrapper);
    }

    @Test  // DELETE FROM tb_user WHERE name=? AND age=?
    public void testDelete4() {
        User user = new User();
        user.setAge(10);
        user.setName("test");
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>(user);
        userMapper.delete(queryWrapper);
    }

    // ----------------查询

    /**
     * SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE id IN ( ? , ? , ? )
     * 更具id s 批量查询
     */
    @Test
    public void testSelect1() {


        List<User> userList = userMapper.selectBatchIds(Arrays.asList("1", "2", "3"));
        userList.forEach(System.out::println);
    }

    /**
     * 查询一条数据，当查询的数据超过一条数会抛出异常
     */
    @Test
    public void testSelectOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "test");
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    // SELECT COUNT( 1 ) FROM tb_user WHERE name = ?
    @Test
    public void testSelectCount() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "test");
        Integer integer = userMapper.selectCount(queryWrapper);
        System.out.println(integer);
    }

    // SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE age > ?
    @Test
    public void testSelectList() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 10);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }


    // -----  分页查询
    // SELECT COUNT(1) FROM tb_user WHERE age > ?
    // SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE age > ? LIMIT ?,?
    @Test
    public void testPage() {
        // 第一页 一条数据
        Page<User> page = new Page<>(2, 1);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 10);

        IPage<User> iPage = userMapper.selectPage(page, queryWrapper);
        System.out.println("数据总条数:" + iPage.getTotal());
        System.out.println("总页数:" + iPage.getPages());
        List<User> records = iPage.getRecords();
        records.forEach(System.out::println);


    }

    @Test
    public void testSelectPage() {
        User user = this.userMapper.findById(2L);
        System.out.println(user);
    }

    // ---------条件构造器
    @Test
    public void testAll() {

        //设置条件
        Map<String, Object> params = new HashMap<>();
        params.put("name", "曹操");
        params.put("age", "20");
        params.put("password", null);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE password IS NULL AND name = ? AND age = ?
//        wrapper.allEq(params);

        // SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE name = ? AND age = ?
        // 是否参数为 null 自动执行 isNull 方法, false 则忽略这个字段\
//        wrapper.allEq(params,false);

        //SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE age = ?
//        wrapper.allEq((k,v) -> (k.equals("age") || k.equals("id")), params);


        List<User> users = this.userMapper.selectList(wrapper);
        users.forEach(System.out::println);


    }

    @Test
    public void testSelect() {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // SELECT id,name,age FROM tb_user WHERE name = ? OR age = ?
        wrapper.eq("name", "李四").or()
                .eq("age", 24)
                .select("id", "name", "age");
        List<User> users = this.userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }


    @Test
    public void testUpdate3() {
        User user = new User();
        user.setAge(20);
        int result = this.userMapper.update(user, null);
        System.out.println("result = " + result);
    }


    @Test
    public void testUpdate11() {
        User user = new User();
        user.setAge(30);
        user.setId(2L);
        user.setVersion(1); //获取到version为1
        int result = this.userMapper.updateById(user);
        System.out.println("result = " + result);
    }

    @Test
    public void testFindAll() {

        List<User> userList = this.userMapper.findAll();
        userList.forEach(System.out::println);
    }


    @Test
    public void testDeleteById() {
        this.userMapper.deleteById(1L);
    }

    @Test
    public void testSelectById() {
        User user = this.userMapper.selectById(10L);
        System.out.println(user);
    }


    @Test
    public void testSelect12() {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // SELECT id,user_name,name,age,email AS mail,version,deleted,sex FROM tb_user WHERE deleted=0 AND sex = ?
        wrapper.eq("sex",SexEnum.WOMAN);
        List<User> users = this.userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
}
