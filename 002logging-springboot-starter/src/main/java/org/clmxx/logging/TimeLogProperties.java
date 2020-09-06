package org.clmxx.logging;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author clx
 * @date 2020-09-06 17:57
 */
@ConfigurationProperties(prefix = "clxmm.time")
public class TimeLogProperties {


    private Boolean enable;


    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
