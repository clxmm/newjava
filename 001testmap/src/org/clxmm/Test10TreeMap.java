package org.clxmm;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * tree map 的 排序
 * @author clx
 * @date 2020-09-06 21:05
 */
public class Test10TreeMap {


    public static void main(String[] args) {


        Map<String,String> map = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // 降序
                return o2.compareTo(o1);
            }
        });


        map.put("q","1");
        map.put("q1","r");
        map.put("q3","r");
        map.put("q4","1");
        map.put("q6","1d");

        System.out.println(map);

    }
}
