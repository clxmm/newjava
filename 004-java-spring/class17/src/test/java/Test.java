import com.i.TestBean;
import com.i.TestConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/26 4:08 下午
 */
public class Test {

    @org.junit.Test
    public void test() {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        TestBean testBean = context.getBean("testBean", TestBean.class);
        System.out.println(testBean);
        context.close();
    }

}
