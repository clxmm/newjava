package org.clxmm.service.vod.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/12/26 8:34 下午
 */
@Data
@Component
@ConfigurationProperties(prefix="aliyun.vod")
public class VodProperties {

    private String keyid;
    private String keysecret;
    private String templateGroupId;
    private String workflowId;
}
