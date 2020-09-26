package i.humen;

import i.demo.Car;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/19 2:46 下午
 */
public class Zs  extends  HumenWithCar{


    public Zs(Car car) {
        super(car);
    }

    public void goHome() {
        car.start();
        car.turnLeft();
        car.turnRight();
        car.start();

    }
}
