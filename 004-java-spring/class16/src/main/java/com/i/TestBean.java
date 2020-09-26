package com.i;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/26 3:55 下午
 */
@Component("testBean1")
@Lazy // 开启懒加载
public class TestBean {

}
