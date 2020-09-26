import i.Bean;
import i.Bean1;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/20 7:26 下午
 */
public class Test {




    @org.junit.Test
    public void test() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");


        Bean bean = context.getBean("bean", Bean.class);

        System.out.println("-- " + bean);


        Bean1 bean1 =context.getBean("bean1",Bean1.class);

        System.out.println("bean1 "+bean1);

        context.close();
        context.close();


    }


}
