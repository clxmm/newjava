import com.i.TestBean;
import com.i.TestConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.security.auth.login.AppConfigurationEntry;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/26 3:56 下午
 */
public class Test1 {

    @Test
    public void test() {

        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        System.out.println("init");

        TestBean testBean1 = context.getBean("testBean1", TestBean.class);
        System.out.println("testBean1:" +testBean1);
        TestBean testBean2 = context.getBean("testBean2", TestBean.class);

        System.out.println("testBean2:" + testBean2);

    }



}
