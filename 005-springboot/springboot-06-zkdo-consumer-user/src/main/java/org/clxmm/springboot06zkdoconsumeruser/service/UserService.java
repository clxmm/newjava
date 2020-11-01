package org.clxmm.springboot06zkdoconsumeruser.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.clxmm.springboot06zkdoprovider.service.TicketService;
import org.springframework.stereotype.Service;

import javax.annotation.Resources;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/31 6:08 下午
 */
@Service
public class UserService {

    @Reference
    TicketService ticketService;


    public void hello() {
        String ticket = ticketService.getTicket();
        System.out.println(ticket);
    }









}
