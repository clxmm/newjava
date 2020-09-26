package i.humen;

import i.demo.Car;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/19 2:45 下午
 */
public  abstract class HumenWithCar implements Humen {
    protected Car car;

    public HumenWithCar(Car car) {
        this.car = car;
    }


    public abstract void goHome();


}
