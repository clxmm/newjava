package org.clxmm.service.trade.service;

import java.util.Map;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/2/21 5:35 下午
 */
public interface WeixinPayService {

    Map<String, Object> createNative(String orderNo, String remoteAddr);
}
