package org.clxmm;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 1. map.forEach
 * @author clx
 * @date 2020-09-06 16:26
 */
public class Test7Map {


    public static void main(String[] args) {
        Map<String, String> map = new HashMap();
        map.put("x", "123");
        map.put("y", "456");




        map.forEach((k,v) -> System.out.println("k"+k + " v "+v));

        System.out.println(map.getOrDefault("x","234"));
    }

}
