package org.clmmm.springboot010cach;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("org.clmmm.springboot010cach.mapper")
@EnableCaching
public class SpringBoot010CachApplication {

	/**
	 * 配置redis
	 * 	1)、 引入redis的starter ，容器中保存的是RedisCacheManager ;
	 * 	2)、 RedisCacheManager 创建redisCache 来作为缓存组件，RedisCache 通过操作redis缓存数据
	 *	3)、 默认保存数据，都是Object 利用序列化保存，如何保存json
	 *		引入redis的starter cacheMange 变为 RedisCacheManager
	 *		默认创建的RedisCacheManager  操作redis 的时候使用RedisTemplate
	 *		RedisTemplate 默认使用jdk的序列化机制
	 *	4） 自定一	RedisCacheManager
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBoot010CachApplication.class, args);
	}

}
