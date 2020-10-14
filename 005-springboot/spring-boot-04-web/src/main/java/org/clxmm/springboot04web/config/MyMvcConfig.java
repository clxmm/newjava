package org.clxmm.springboot04web.config;


import org.clxmm.springboot04web.component.MyLocaleResolver;
import org.clxmm.springboot04web.component.MyLoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 使用WebMvcConfigurer 扩展 spring mvc
 *
 * @author clxmm
 * @version 1.0
 * @date 2020/10/6 1:40 下午
 */
//@EnableWebMvc
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        // 浏览器发送 testV 请求 到success 页面
        registry.addViewController("/testV").setViewName("success");
    }

    // 所有的 webMvcConfigurer 组件会一起起作用
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            // 注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(new MyLoginHandlerInterceptor()).addPathPatterns("/main.html","/test")
//                        .excludePathPatterns("/index.html", "/", "/user/login");
                // 静态资源， *。css js
                //spring boot 已经做好了静态资源映射
                //
            }
        };



        return webMvcConfigurer;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }


}
