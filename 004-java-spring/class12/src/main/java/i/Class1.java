package i;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/20 7:38 下午
 */
public class Class1 extends ParentClass {


    private String cs1;
    private String cs2;


    public Class1() {
    }


    public String getCs1() {
        return cs1;
    }

    public void setCs1(String cs1) {
        this.cs1 = cs1;
    }

    public String getCs2() {
        return cs2;
    }

    public void setCs2(String cs2) {
        this.cs2 = cs2;
    }

    @Override
    public String toString() {
        return "Class1{" +
                "cs1='" + cs1 + '\'' +
                ", cs2='" + cs2 + '\'' +
                '}' +

                "s1='" + getS1() + '\'' +
                ", s2='" + getS2() + '\'' +
                ", s3='" + getS3() + '\'' +
                '}';
    }
}
