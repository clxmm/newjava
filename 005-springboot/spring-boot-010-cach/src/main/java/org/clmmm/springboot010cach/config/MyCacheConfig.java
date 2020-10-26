package org.clmmm.springboot010cach.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/17 8:22 下午
 */
@Configuration
public class MyCacheConfig {



        @Bean("myKeyGenerator")
        public KeyGenerator keyGenerator() {
            return new KeyGenerator() {
                @Override
                public Object generate(Object target, Method method, Object... params) {


                    return method.getName()+"[" + Arrays.asList(params).toString()+"]";
                }
            };
        }


}
