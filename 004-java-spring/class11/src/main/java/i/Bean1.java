package i;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 *
 * 继承spring 的接口实现bean的初始化和销毁
 * @author clxmm
 * @version 1.0
 * @date 2020/9/20 7:23 下午
 */
public class Bean1 implements InitializingBean, DisposableBean {


    public Bean1() {
        System.out.println("bean1 ");
    }


    public void destroy() throws Exception {

        System.out.println("bean1 销毁");
    }

    public void afterPropertiesSet() throws Exception {

        System.out.println("bean1 初始化");
    }
}
