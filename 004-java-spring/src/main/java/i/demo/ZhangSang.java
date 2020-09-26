package i.demo;


/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/19 2:20 下午
 */


public class ZhangSang {

//    private Aodi aodi = new Aodi();


    private Car aodi;

    // 由ioc 去创建
    public ZhangSang(Car car) {
        this.aodi = car;
    }

    public void goHome() {

//        Aodi aodi = new Aodi();
        aodi.start();
        aodi.turnLeft();
        aodi.turnRight();
        aodi.stop();
    }

    public void goSchool() {

//        Aodi aodi = new Aodi();
        aodi.start();
        aodi.turnLeft();
        aodi.turnRight();
        aodi.stop();
    }

}
