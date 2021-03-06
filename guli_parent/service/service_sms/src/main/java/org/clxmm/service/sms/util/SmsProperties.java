package org.clxmm.service.sms.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/2/2 7:51 下午
 */

@Data
@Component
@ConfigurationProperties(prefix="aliyun.sms")
public class SmsProperties {

    private String regionId;
    private String keyId;
    private String keySecret;
    private String templateCode;
    private String signName;
}
