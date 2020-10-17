package org.clxmm.clxmmspringbootstarterautoconfigura.starter;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/17 3:53 下午
 */
public class HelloService {

    HelloProperties helloProperties;

    public String sayHello(String name) {

        return helloProperties.getPrefix() + "-" + name + "-" + helloProperties.getSuffix();
    }


    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }
}
