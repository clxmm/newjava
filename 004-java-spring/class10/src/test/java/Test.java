import i.Bean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/20 7:07 下午
 */
public class Test {


    @org.junit.Test
    public void test() {


        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        System.out.println("context ");

        Bean bean = context.getBean("baen",Bean.class);

    }
}
