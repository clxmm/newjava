package org.clmmm.springboot010cach;

import org.clmmm.springboot010cach.bean.Employee;
import org.clmmm.springboot010cach.mapper.EmployeeMapper;
import org.clmmm.springboot010cach.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SpringBoot010CachApplicationTests {

    @Autowired
    EmployeeMapper mapper;

    @Test
    void contextLoads() {


        Employee emp = mapper.getEmpById(1);
        System.out.println(emp);

    }

    @Autowired
    RedisTemplate redisTemplate;   // key v
    @Autowired
    StringRedisTemplate stringRedisTemplate; // string


    /**
     * redis 常用的数据类型
     * string list set hash zset
     * stringRedisTemplate.opsForValue()    string
     * opsForList()     list
     * opsForSet()   set
     * opsForHash()  hash
     * opsForZSet  zset
     */
    @Test
    public void test01() {
//		stringRedisTemplate.opsForValue().append("msg","hello");
//		redisTemplate.opsForValue().append("msg1","hello");

        // 获取字符串
        System.out.println(stringRedisTemplate.opsForValue().get("msg"));

        //
        stringRedisTemplate.opsForList().leftPush("mylist", String.valueOf(12));
        stringRedisTemplate.opsForList().leftPush("mylist", String.valueOf(123));


    }


    @Autowired
    RedisTemplate<Object, Employee> empRedisTemplate;

    // redis 保存对象
    @Test
    public void test02() {

        Employee emp = mapper.getEmpById(1);
        // 默认保存对象，使用jdk 序列化机制，序列化后的数据保存到redis中
        redisTemplate.opsForValue().set("emp-00", emp);

        // 1， 将数据一json 的方式保存
        // （1），自己将对象转为json
        // （2）

        empRedisTemplate.opsForValue().set("emp-01", emp);

    }


}
