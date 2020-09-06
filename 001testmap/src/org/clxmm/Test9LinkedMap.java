package org.clxmm;

import org.clxmm.common.LRUMap;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author clx
 * @date 2020-09-06 16:49
 */
public class Test9LinkedMap {


    public static void main(String[] args) {

        Map<String, String> map = new LinkedHashMap<String, String>(16, 0.75f, true);

        map.put("x", "1");
        map.put("e", "2");
        map.put("r", "5");
        System.out.println(map);

        map.get("e");
        System.out.println(map);


        LRUMap<String, String> map2 = new LRUMap<>(3);
        map2.put("q", "2");
        map2.put("w", "21");
        map2.put("e", "22");

        map2.get("q");
        map2.put("r", "22");
        map2.put("t", "22");


        System.out.println(map2);
    }
}
