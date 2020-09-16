package org.clxmm.TestSerizable;

import java.io.IOException;
import java.io.Serializable;

/**
 * 学生类
 * @author clxmm
 * @version 1.0
 * @date 2020/9/15 8:47 下午
 */
public class Student implements Serializable {

    private String stuno;

    private String stuname;

    // transient 修饰的不会进行jvm的被序列化 也可以自己完成这个元素的序列化
    private transient String stuage;


    // 序列化
     private void writeObject(java.io.ObjectOutputStream s) throws IOException, ClassNotFoundException {
         s.defaultWriteObject();  // 把jvm能默认序列化的元素进行序列化操作
         s.writeObject(stuage);  // 自己完成stuage 的序列化
     }

     // 反序列化
    private void readObject(java.io.ObjectInputStream s) throws  IOException,ClassNotFoundException {
         s.defaultReadObject(); // 将jvm能默认反序列化的元素进行反序列化操作
        Object i = s.readObject();
        this.stuage = String.valueOf(i); //完成自己stuage的反序列化操作
    }



    public Student() {

    }

    public Student(String stuno, String stuname, String stuage) {
        this.stuno = stuno;
        this.stuname = stuname;
        this.stuage = stuage;
    }

    public String getStuno() {
        return stuno;
    }

    public void setStuno(String stuno) {
        this.stuno = stuno;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getStuage() {
        return stuage;
    }

    public void setStuage(String stuage) {
        this.stuage = stuage;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuno='" + stuno + '\'' +
                ", stuname='" + stuname + '\'' +
                ", stuage='" + stuage + '\'' +
                '}';
    }
}
