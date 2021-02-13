package org.clxmm.service.trade.service.impl;

import org.clxmm.service.trade.entity.Order;
import org.clxmm.service.trade.mapper.OrderMapper;
import org.clxmm.service.trade.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author clxmm
 * @since 2021-02-13
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
