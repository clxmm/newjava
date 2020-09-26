import com.i.MyConfig;
import com.i.com.AnotherBean;
import com.i.com.MyBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/25 8:42 下午
 */
public class MyTest {

    @Test
    public void test() {

        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        MyBean myBean = context.getBean("myBean", MyBean.class);
        System.out.println(myBean);
        myBean.getStringList().stream().forEach(System.out::println);

        System.out.println(myBean.getIntegerMap());


        AnotherBean anotherBean = myBean.getContext().getBean("anotherBean", AnotherBean.class);
        System.out.println(anotherBean);

    }


}
