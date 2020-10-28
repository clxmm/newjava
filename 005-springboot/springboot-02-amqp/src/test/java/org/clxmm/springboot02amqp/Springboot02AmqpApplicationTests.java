package org.clxmm.springboot02amqp;

import org.clxmm.springboot02amqp.bean.Book;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class Springboot02AmqpApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;


	/**
	 * 1, 单播，点对点模式
	 */
	@Test
	void contextLoads() {
		//Message 需要自己构造一个，定义消息题内容和消息头，
//		rabbitTemplate.send(exchange,routeKey,messgae);

		// object 默认成消息体，传入要发送的对象，自动序列化给rabbitmq
//		rabbitTemplate.convertAndSend(exchange,routeKey,object);
		Map<String,Object> map = new HashMap<>();
		map.put("msg","消息");
		map.put("data", Arrays.asList("1","2","3"));
		// 对象默认序列化之后发送出去
		rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",map);

	}

	// 接受数据
	@Test
	public  void receive() {
		Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
		System.out.println(o.getClass());
		System.out.println(o);
	}

	//，使用json 去序列化


	/**
	 * 广播
	 */
	@Test
	public void senMsg() {
		Map<String,Object> map = new HashMap<>();
		map.put("msg","消息");
		map.put("data", Arrays.asList("1","2","3"));
		Book book = new Book();
		book.setName("楚留香");
		book.setPrice(12L);
		rabbitTemplate.convertAndSend("exchange.fanout","",book);
	}



	@Autowired
	AmqpAdmin amqpAdmin;



	@Test
	public void createExchange() {

		// 创建exchange
//		amqpAdmin.declareExchange(new DirectExchange("clx"));
//		System.out.println("创建完成");

		// 创建queues

//		amqpAdmin.declareQueue(new Queue("clx.queue",true));


		amqpAdmin.declareBinding(new Binding("clx.queue", Binding.DestinationType.QUEUE,"clx","amqp.haha",null));





	}











}
