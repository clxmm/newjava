package org.clxmm.clxmmspringbootstarterautoconfigura.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/17 4:08 下午
 */
@ConfigurationProperties(prefix = "clxmm.hello")
public class HelloProperties {

    private String prefix;

    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
