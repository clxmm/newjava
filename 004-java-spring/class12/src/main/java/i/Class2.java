package i;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/20 7:39 下午
 */
public class Class2 extends ParentClass {


    private String c21;


    public Class2() {
    }


    public String getC21() {
        return c21;
    }

    public void setC21(String c21) {
        this.c21 = c21;
    }


    @Override
    public String toString() {
        return "Class2{" +
                "c21='" + c21 + '\'' +
                '}' +
        "s1='" + getS1() + '\'' +
                ", s2='" + getS2() + '\'' +
                ", s3='" + getS3() + '\'' +
                '}';
    }
}
