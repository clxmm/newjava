package org.clxmm.service.trade.service.impl;

import org.clxmm.service.trade.entity.PayLog;
import org.clxmm.service.trade.mapper.PayLogMapper;
import org.clxmm.service.trade.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author clxmm
 * @since 2021-02-13
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

}
