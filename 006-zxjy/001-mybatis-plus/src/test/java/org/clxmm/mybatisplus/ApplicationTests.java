package org.clxmm.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.clxmm.mybatisplus.entity.User;
import org.clxmm.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ApplicationTests {


	@Autowired
	private UserMapper userMapper;

	@Test
	void contextLoads() {

		//UserMapper 中的 selectList() 方法的参数为 MP 内置的条件封装器 Wrapper
		//所以不填写就是无任何条件
		List<User> users = userMapper.selectList(null);

		users.forEach(System.out::println);
	}

	// 测试insert
	@Test
	public void testInsert() {
		User user = new User();
		user.setAge(18);
		user.setName("clx1");
		user.setEmail("1152441@qq.com");

		int insert = userMapper.insert(user);
		System.out.println("受影响的行数" + insert);
		// 数据库插入id值默认为：全局唯一id
		System.out.println("id: " + user.getId());    //id自动回填

	}


	@Test
	public void testUpdateBtId() {
		User user = new User();
		user.setId(1323273329552965633L);
		user.setAge(20);

		int i = userMapper.updateById(user);
		System.out.println("受影响的行数" + i);

	}


	// 批量查询
	@Test
	public void testSelectByIds() {
		List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
		users.forEach(System.out::println);
	}

	// 条件查询
	@Test
	public void testByMap() {
		Map<String,Object> map = new HashMap<>();
		map.put("name","Helen");
		map.put("age",18);

		List<User> users = userMapper.selectByMap(map);
		for (User user : users) {
			System.out.println(user);
		}
	}

	// 分页查询
	@Test
	public void testPage() {

		Page<User> page = new Page<>(1,5);
		Page<User> page1 = userMapper.selectPage(page, null);
		page1.getRecords().forEach(System.out::println);
	}

	// 返回指定的列
	@Test
	public void testPage1() {
		//page 不需要泛型
		Page<Map<String,Object>> page = new Page<>(1,5);
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.select("name","age");
		Page<Map<String, Object>> page1 = userMapper.selectMapsPage(page, queryWrapper);
		page1.getRecords().forEach(System.out::println);
	}


	@Test
	public void testDelete1() {
		int i = userMapper.deleteById(1L);
		System.out.println("result :" + i);
	}


	@Test
	public void testDelete2() {
		int i = userMapper.deleteBatchIds(Arrays.asList(6L, 7L));
		System.out.println("result " + i);
	}



	@Test
	public void deleteByNMap() {
		Map<String,Object> map = new HashMap<>();
		map.put("name","Helen");
		map.put("age",18L);

		int i = userMapper.deleteByMap(map);
		System.out.println("result " + i);


	}





}
