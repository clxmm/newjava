import i.Slbean.Bean2;
import i.Slbean.Bean3;
import i.springioc.Bean;
import i.test6zhurubean.Beano;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/19 8:15 下午
 */

public class Test2Springioc {




    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Bean bean = context.getBean("bean", Bean.class);
        System.out.println(bean);


        Bean2 bean2 = context.getBean("bean2", Bean2.class);
        System.out.println(bean2);


        Bean3 bean3 = context.getBean("bean3", Bean3.class);
        System.out.println(bean3);


        Bean bean_1 = context.getBean("bean_1", Bean.class);
        System.out.println(bean_1);

        Bean bean_3 = context.getBean("bean_3", Bean.class);
        System.out.println(bean_3);

    }

    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Beano bean0 = context.getBean("bean0", Beano.class);
        System.out.println(bean0);
        System.out.println(bean0.getAnotherBean2());


    }


}
