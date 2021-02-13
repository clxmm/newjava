//package org.clxmm.service.base.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.clxmm.common.base.result.ResultCodeEnum;
//import org.clxmm.service.base.exception.ClxmmException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//import org.springframework.session.web.http.CookieSerializer;
//import org.springframework.session.web.http.DefaultCookieSerializer;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import sun.rmi.runtime.Log;
//
//import javax.servlet.http.HttpSession;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author clxmm
// * @version 1.0
// * @date 2021/2/10 12:27 下午
// */
//@Configuration
//@EnableRedisHttpSession
//@Slf4j
//public class HttpSessionConfig {
//
//
//    //可选配置
//    @Bean
//    public CookieSerializer cookieSerializer() {
//        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
//        //我们可以将Spring Session默认的Cookie Key从SESSION替换为原生的JSESSIONID
//        serializer.setCookieName("JSESSIONID");
//        // CookiePath设置为根路径
//        serializer.setCookiePath("/");
//        // 配置了相关的正则表达式，可以达到同父域下的单点登录的效果
//        serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
//        return serializer;
//    }
//
//}
