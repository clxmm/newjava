import i.Test7zuoyonyu.Beano1;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/19 9:24 下午
 */
public class Test7BeanScop {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");


//        Beano2 beano02_1 = context.getBean("beano2", Beano2.class);
//        Beano2 beano2_2 = context.getBean("beano2", Beano2.class);
//        System.out.println(beano02_1);
//
//        System.out.println(beano2_2);
         Beano1 beano1 = context.getBean("beano1",Beano1.class);
         beano1.printBean2();
         beano1.printBean2();
         beano1.printBean2();
        System.out.println(beano1);



    }
}
