import com.i.TestBean;
import com.i.TestConfigure;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/26 3:37 下午
 */
public class Test {




    @org.junit.Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfigure.class);

//        for (int i = 0; i < 10; i++) {
//        TestBean testBean1 = context.getBean("testBean1", TestBean.class);
//        TestBean testBean2 = context.getBean("testBean2", TestBean.class);
//            System.out.println(testBean1);
//            System.out.println(testBean2);
//        }

        for (int i = 0; i < 10; i++) {
        TestBean testBean2 = context.getBean("testBean2", TestBean.class);
//            System.out.println(testBean2);
            testBean2.pringtAnotherBean();
        }







    }
}
