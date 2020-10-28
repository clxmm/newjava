package org.clxmm.springboot02amqp.service;

import org.clxmm.springboot02amqp.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/28 8:07 下午
 */
@Service
public class BookService {

    @RabbitListener(queues = "atguigu.news")
    public void receive(Book book) {
        System.out.println("收到消息"+book);
    }



    @RabbitListener(queues = "atguigu")
    public void receive01(Message message) {

        System.out.println("消息内容：" + message.getBody());
        System.out.println(message.getMessageProperties());

    }

}
