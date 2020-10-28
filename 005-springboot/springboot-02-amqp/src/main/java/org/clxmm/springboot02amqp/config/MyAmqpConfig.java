package org.clxmm.springboot02amqp.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.Jackson2XmlMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/27 9:31 下午
 */
@Configuration
public class MyAmqpConfig {

    @Bean

    public MessageConverter messageConverter () {

        return new Jackson2JsonMessageConverter();
    }

}
