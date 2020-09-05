package org.clxmm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author clx
 * @date 2020-09-02 20:37
 */
public class Test2Map {

    private static Map<String, Integer> init() {
        Map<String, Integer> testMap = new HashMap<>();
        String str[] = new String[]{"a", "b", "c", "d", "w", "q", "w", "e", "r", "r"};
        String key = "";
        Integer val = 0;
        for (int i = 0; i < 10_000; i++) {
            int m = (int) (Math.random() * 10);
            key = String.valueOf(str[m]) + i * 100;
            val = i;
            testMap.put(key, val);

        }
        return testMap;
    }


    public static void main(String[] args) {
        Map<String, Integer> map1 = init();

//        showMap1(map1);

//        showMap2(map1);

//        showMap3(map1);
        showMap4(map1);

    }

    /**
     * 利用 iterator 遍历map 集合
     *
     * @param map
     */
    private static void showMap4(Map<String, Integer> map) {
        long start = System.currentTimeMillis();
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> next = iterator.next();
            System.out.println(next.getKey() + "; " + next.getValue());
        }
        long end = System.currentTimeMillis();
        System.out.println("values st->end" + (end - start));
    }

    /**
     * 遍历map 中的entrySet
     *
     * @param map
     */
    private static void showMap3(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "--" + entry.getValue());
        }
    }

    /**
     * 遍历map 中的values
     *
     * @param map
     */
    private static void showMap2(Map<String, Integer> map) {
        long start = System.currentTimeMillis();
        for (Integer values : map.values()) {
            System.out.println("values: " + values);
        }
        long end = System.currentTimeMillis();
        System.out.println("values st->end" + (end - start));
    }

    /**
     * 利用 keySet 进行遍历
     *
     * @param map
     */
    private static void showMap1(Map<String, Integer> map) {
        long start = System.currentTimeMillis();
        for (String o : map.keySet()) {
            System.out.println("key: " + o + " value:" + map.get(o));
        }
        long end = System.currentTimeMillis();
        System.out.println("values st->end" + (end - start));
    }


}
