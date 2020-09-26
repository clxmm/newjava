import i.IocContainer;
import i.demo.Aodi;
import i.demo.Bieke;
import i.humen.Humen;
import i.humen.Ls;
import i.humen.Zs;
import org.junit.Before;
import org.junit.Test;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/19 3:08 下午
 */
public class TestClass {


    private IocContainer iocContainer = new IocContainer();

    @Before
    public void before() {

        iocContainer.setBean(Aodi.class,"aoid");
        iocContainer.setBean(Bieke.class,"bieke");
        iocContainer.setBean(Zs.class,"zs","aoid");
        iocContainer.setBean(Ls.class,"ls","bieke");

    }


    @Test
    public void test1() {
        Humen zs = (Humen) iocContainer.getBean("zs");
        zs.goHome();
        System.out.println("lis ");
        Humen li = (Humen) iocContainer.getBean("ls");
        li.goHome();

    }





}
