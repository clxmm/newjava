import i.Test7zuoyonyu.Beano2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/20 6:50 下午
 */
public class Test8 {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");


        for (int i = 0; i < 10; i++) {
            
            Beano2 beano2 = context.getBean("beano_1", Beano2.class);
            System.out.println("beano2 = " +beano2);
        }
    }

}
