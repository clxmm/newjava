package org.clxmm;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author clx
 * @date 2020-09-06 16:33
 */
public class Test8LinkedMap {


    public static void main(String[] args) {

        int count = 1000_000;

        Map<String, String> map1 = new HashMap<>();  // 774
        Map<String, String> map2 = new LinkedHashMap<>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            map1.put(i + "", "v");
        }
        long end = System.currentTimeMillis();


        System.out.println("time:" + (end - start));


        long start1 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            map2.put(i + "", "v");
        }
        long end1 = System.currentTimeMillis();


        System.out.println("time:" + (end1 - start1));


        long start3 = System.currentTimeMillis();
        for (String value : map1.values()) {

        }
        long end3 = System.currentTimeMillis();


        System.out.println("time:" + (end3 - start3));


        long start4 = System.currentTimeMillis();
        for (String value : map2.values()) {

        }
        long end4 = System.currentTimeMillis();
        System.out.println("time:" + (end4 - start4));


    }


}
