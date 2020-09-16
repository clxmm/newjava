package org.clxmm.TestSerizable;

import java.io.*;

/**
 * 测试对象序列化
 *
 * @author clxmm
 * @version 1.0
 * @date 2020/9/15 8:50 下午
 */
public class Test1ObjectSerializable {


    public static void main(String[] args) throws IOException,ClassNotFoundException {

        String file = "demo/obj.dat";
//        // 对象的序列化
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
//        Student student = new Student("1001", "tom", "123");
//        oos.writeObject(student);
//        oos.flush();
//        oos.close();

        // 反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));


        Student stu = (Student) ois.readObject();

        System.out.println(stu);
        ois.close();



    }


}
