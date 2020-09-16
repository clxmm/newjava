package org.clxmm;

import java.io.File;
import java.io.IOException;

/**
 * 常用api
 * @author clx
 * @date 2020-09-07 20:29
 */
public class Test2File {


    public static void main(String[] args) {
        //
        File file = new File("D:\\resourceStudy\\imooc\\test\\");


        System.out.println("是否存在：" + file.exists());

        if (!file.exists()) {
            file.mkdir();
            // 创建多级目录
//            file.mkdirs()
        } else {
//            file.delete();
        }

        // 不存在或者不是目录 -> false
        System.out.println("是否是目录：" + file.isDirectory());

        System.out.println("是否是文件：" + file.isFile());

        File file1 = new File("D:\\resourceStudy\\imooc\\test\\1.txt");

        if (!file1.exists()) {
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 常用api
        System.out.println(file.toString());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getName());
        System.out.println(file1.getName());


        System.out.println(file.getParent());

    }
}
package org.clxmm;

import java.io.File;
import java.io.IOException;

/**
 * 常用api
 * @author clx
 * @date 2020-09-07 20:29
 */
public class Test2File {


    public static void main(String[] args) {
        //
        File file = new File("D:\\resourceStudy\\imooc\\test\\");


        System.out.println("是否存在：" + file.exists());

        if (!file.exists()) {
            file.mkdir();
            // 创建多级目录
//            file.mkdirs()
        } else {
//            file.delete();
        }

        // 不存在或者不是目录 -> false
        System.out.println("是否是目录：" + file.isDirectory());

        System.out.println("是否是文件：" + file.isFile());

        File file1 = new File("D:\\resourceStudy\\imooc\\test\\1.txt");

        if (!file1.exists()) {
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 常用api
        System.out.println(file.toString());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getName());
        System.out.println(file1.getName());


        System.out.println(file.getParent());

    }
}
