package com.clxmm.example3;


import java.util.ArrayList;
import java.util.List;

/**
 * <h1> 字符串，数组，集合时出现空指针</h1>
 * @author clxmm
 * @version 1.0
 * @date 2020/9/26 8:05 下午
 */
public class BasicUsageNpe {

    private static boolean stringEquals(String x,String y) {
        return  x.equals(y);
    }

    public  static  class  User {
        String name;
    }


    public static void main(String[] args) {

        // 字符串 equals
//        System.out.println(stringEquals("x",null));
//        System.out.println(stringEquals(null,"ttt"));   // 报错

        User[] users = new User[10];  // 只是声明了
/*        for (int i = 0; i < users.length; i++) {

            users[i].name = "1" + i; // 报错
        }*/


        // 3。 list addAll null 报错
        /*List<User> users1  = new ArrayList<>();

        User user = null;

        List<User> users_ = null;

        users1.add(user);
        users1.addAll(users_);  //  报错*/






    }



}
