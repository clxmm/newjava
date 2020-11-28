package org.clxmm.service.oss.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/11/16 9:29 下午
 */
@Component
@Data
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssProperties {

    private String endpoint;

    private String keyid;

    private String keysecret;

    private String bucketname;

}
