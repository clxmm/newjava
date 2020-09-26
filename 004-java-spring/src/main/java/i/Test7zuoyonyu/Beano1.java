package i.Test7zuoyonyu;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/19 9:20 下午
 */
public  abstract  class Beano1 {
//    private Beano2 beano2;
//
//    public Beano2 getBeano2() {
//        return beano2;
//    }
//
//    public void setBeano2(Beano2 beano2) {
//        this.beano2 = beano2;
//    }



    protected   abstract  Beano2 CreateBeano2();

    public void printBean2() {
        System.out.println(CreateBeano2());
    }



}
