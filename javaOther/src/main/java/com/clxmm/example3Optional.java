package com.clxmm;

import java.util.Optional;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/27 8:24 下午
 */
public class example3Optional {

    public static class User {
        private String name;

        public String getName() {
            return name;
        }
    }


    private static void isUserEqualNull() {


        User user = null;
        if (user != null) {

            System.out.println("user is not null");

        } else {
            System.out.println("user is null");

        }

        Optional<User> optional = Optional.empty();

        if (optional.isPresent()) {
            System.out.println("user is not null");
        } else {
            System.out.println("user is null");
        }


    }

    private static User anoymos() {
        return new User();
    }


    public static void main(String[] args) {
        // 没有意义的使用方法
        isUserEqualNull();


        User user = null;

        Optional<User> optionalUser = Optional.ofNullable(user);
        optionalUser.orElse(new User());

        optionalUser.orElseGet(() -> anoymos());

        optionalUser.orElseThrow(RuntimeException::new);

        optionalUser.ifPresent(u-> System.out.println(u.getName()));


        optionalUser.map(u -> u.getName()) .orElse("anymos");







    }


}
