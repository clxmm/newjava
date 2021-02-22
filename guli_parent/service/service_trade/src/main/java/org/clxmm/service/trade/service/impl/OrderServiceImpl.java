package org.clxmm.service.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import org.clxmm.common.base.result.ResultCodeEnum;
import org.clxmm.service.base.dto.CourseDto;
import org.clxmm.service.base.dto.MemberDto;
import org.clxmm.service.base.exception.ClxmmException;
import org.clxmm.service.trade.entity.Order;
import org.clxmm.service.trade.entity.PayLog;
import org.clxmm.service.trade.feign.EduCourseService;
import org.clxmm.service.trade.feign.UcenterMemberService;
import org.clxmm.service.trade.mapper.OrderMapper;
import org.clxmm.service.trade.mapper.PayLogMapper;
import org.clxmm.service.trade.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.clxmm.service.trade.util.OrderNoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.OrderUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @Autowired
    private PayLogMapper payLogMapper;

    @Override
    public String saveOrder(String courseId, String id) {

        // 查询用户是否已经下了订单，
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getCourseId, courseId)
                .eq(Order::getMemberId, id);

        Order orderExist = baseMapper.selectOne(queryWrapper);
        if (orderExist != null) {
            // 如果订单已存在，则直接返回id
            return orderExist.getId();
        }
        // 查询课程信息
        CourseDto courseDtoById = eduCourseService.getCourseDtoById(courseId);

        if (courseDtoById == null) {
            throw new ClxmmException(ResultCodeEnum.PARAM_ERROR);
        }

        // 查询用户信息
        MemberDto memberDto = ucenterMemberService.getMemberDtoByMemberId(id);
        if (memberDto == null) {
            throw new ClxmmException(ResultCodeEnum.PARAM_ERROR);
        }

        // 创建订单
        Order order = new Order();
        order.setOrderNo(OrderNoUtils.getOrderNo()); // 订单号
        order.setCourseId(courseId);
        order.setCourseTitle(courseDtoById.getTitle());
        order.setCourseCover(courseDtoById.getCover());
        order.setTeacherName(courseDtoById.getTeacherName());
        order.setTotalFee(courseDtoById.getPrice().multiply(new BigDecimal(100)));  // 单位 分

        // 会员信息
        order.setMemberId(memberDto.getId());
        order.setMobile(memberDto.getMobile());
        order.setNickname(memberDto.getNickname());

        order.setStatus(0);  // 状态
        order.setPayType(1); // 1 ，微信支付
        baseMapper.insert(order);

        return order.getId();


    }

    @Override
    public Order getByOrderId(String orderId, String memberId) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Order::getId, orderId)
                .eq(Order::getMemberId, memberId);
        Order order = baseMapper.selectOne(queryWrapper);
        return order;
    }

    @Override
    public Boolean isBuyByCourseId(String courseId, String memberId) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Order::getCourseId, courseId)
                .eq(Order::getMemberId, memberId)
                .eq(Order::getStatus, 1);
        int order = baseMapper.selectCount(queryWrapper);

        return order > 0;
    }

    @Override
    public List<Order> selectByMemberId(String memberId) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getMemberId, memberId)
                .orderByDesc(Order::getGmtCreate);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public boolean removeById(String orderId, String memberId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("id", orderId)
                .eq("member_id", memberId);
        return this.remove(queryWrapper);
    }

    @Override
    public Order getOrderByOrderNo(String orderNo) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);
        return baseMapper.selectOne(queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateOrderStatus(Map<String, String> map) {
        //更新订单状态
        String orderNo = map.get("out_trade_no");
        Order order = this.getOrderByOrderNo(orderNo);
        order.setStatus(1);//支付成功
        baseMapper.updateById(order);
        //记录支付日志
        PayLog payLog = new PayLog();
        payLog.setOrderNo(orderNo);
        payLog.setPayTime(new Date());
        payLog.setPayType(1);//支付类型
        payLog.setTotalFee(Long.parseLong(map.get("total_fee")));//总金额(分)
        payLog.setTradeState(map.get("result_code"));//支付状态
        payLog.setTransactionId(map.get("transaction_id"));
        payLog.setAttr(new Gson().toJson(map));
        payLogMapper.insert(payLog);

        //更新课程销量：有问题直接熔断
        eduCourseService.updateBuyCountById(order.getCourseId());
    }

    @Override
    public boolean queryPayStatus(String orderNo) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);
        Order order = baseMapper.selectOne(queryWrapper);
        return order.getStatus() == 1;
    }
}
