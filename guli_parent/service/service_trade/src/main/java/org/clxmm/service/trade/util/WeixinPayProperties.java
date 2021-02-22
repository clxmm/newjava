package org.clxmm.service.trade.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/2/21 5:39 下午
 */
@Data
@Component
@ConfigurationProperties(prefix="weixin.pay")
public class WeixinPayProperties {

    private String appId;
    private String partner;
    private String partnerKey;
    private String notifyUrl;


}
