package org.clxmm.springboot06zkdoprovider.service;

import org.springframework.stereotype.Component;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/31 6:28 下午
 */
@Component

@com.alibaba.dubbo.config.annotation.Service

public class TicketServiceImple  implements TicketService{
    @Override
    public String getTicket() {
        return  "姜子牙";
    }
}
