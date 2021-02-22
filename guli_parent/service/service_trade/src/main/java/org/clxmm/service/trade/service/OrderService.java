package org.clxmm.service.trade.service;

import org.clxmm.service.trade.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author clxmm
 * @since 2021-02-13
 */
public interface OrderService extends IService<Order> {

    String saveOrder(String courseId, String id);

    Order getByOrderId(String orderId, String memberId);

    Boolean isBuyByCourseId(String courseId, String memberId);

    /**
     *  根据会员id获取订单列表
     * @param memberId
     * @return
     */
    List<Order> selectByMemberId(String memberId);

    boolean removeById(String orderId, String memberId);

    Order getOrderByOrderNo(String orderNo);

    void updateOrderStatus(Map<String, String> map);

    boolean queryPayStatus(String orderNo);
}
