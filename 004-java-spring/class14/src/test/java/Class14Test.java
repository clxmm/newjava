

import com.i.MyConfiguration;
import com.i.beab.Bean1;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/25 8:15 下午
 */
public class Class14Test {



    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);

        Bean1 bean1 = context.getBean("bean11", Bean1.class);
        System.out.println(bean1);


    }
}
