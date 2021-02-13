package org.clxmm.service.ucenter.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/2/10 11:50 上午
 */
@Data
@Component
@ConfigurationProperties(prefix = "wx.open")
public class UcenterProperties {

    private String appId;
    private String appSecret;
    private String redirectUri;

}
