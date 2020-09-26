import i.Class1;
import i.Class2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/21 8:56 下午
 */
public class Test {




    @org.junit.Test
    public void test() {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        Class1 class1 = context.getBean("class1", Class1.class);
        System.out.println(class1);


        Class2 class2 = context.getBean("class2", Class2.class);
        System.out.println(class2);


    }

}
