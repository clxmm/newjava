package i;

import java.sql.SQLOutput;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/20 7:20 下午
 */
public class Bean {


    public Bean() {
        System.out.println("bean ");
    }





    public void init() {
        System.out.println("bean 初始化");
    }


    public void destroy() {
        System.out.println("bean 销毁");

    }
}
