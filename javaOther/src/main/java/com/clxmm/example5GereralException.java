package com.clxmm;

import com.google.common.base.Enums;

import java.util.*;

/**
 *
 * <h1>常见的异常</h1>
 * @author clxmm
 * @version 1.0
 * @date 2020/9/28 8:21 下午
 */
public class example5GereralException {

    public static class  User {
        private String name;

        public User(String name) {
            this.name = name;
        }

        public  String getName () {
            return name;
        }

        public User() {}

    }

    public static  class  U1 extends  User {};
    public static  class U2 extends  User {};


    // 并发修改异常
    public static  void test1(List<User> users) {
        for (User user : users) {
            if (user.getName().equals("i")) {
                users.remove(user);
            }
        }
    }

    public static void test2(ArrayList<User> users) {

        Iterator<User> iter = users.iterator();

        while (iter.hasNext()) {
            User next = iter.next();

            if (next.getName().equals("i")) {
                iter.remove();
            }
        }


    }



    private  static final Map<String,StaffType> typeIndex = new HashMap<>();

    static {
        for (StaffType value: StaffType.values()) {
            typeIndex.put(value.name(), value);
        }

    }

    private static  StaffType emGetValue(String type) {

//        typeΩreturn StaffType.valueOf(type);


        // 解决
//        1。 try 捕获

        //  2。  效率不高
/*        for (StaffType value: StaffType.values()) {
            if (value.name().equals(type)) {
                return  value;
            }
        }
        return  null;*/

        //  静态map 索引 初始化一次
        //return typeIndex.get(type);


        // 4. goole guava   相关依赖
        return Enums.getIfPresent(StaffType.class,type).orNull();

    }


    public static void main(String[] args) {
        ArrayList<User> i = new ArrayList<>(Arrays.asList(new User("i"), new User("1")));


        test2(i);



        User u1 = new U1();
        User u2 = new U2();
        // 类型转换异常
//        U2 m2 = (U2) u1;

        // 3. 枚举查找异常
        System.out.println(emGetValue("RD"));
        System.out.println(emGetValue("aaa"));


    }


}
