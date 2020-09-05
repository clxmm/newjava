package org.clxmm;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建10 个 hashmap 每个有 10 条数据
 * 传递不同的构造方法，比较速度
 * 16 和 0.75f   107111180
 * 16384和0.75f  89398540
 *
 * @author clx
 * @date 2020-09-05 20:54
 */
public class Test5Map {


    public static void main(String[] args) {

        Long sum = 0L;


        for (int i = 0; i < 10; i++) {
            sum += new Test5Map().initMap(16384, 0.75f);
        }
        System.out.println(sum + (sum / 10));
    }


    public Long initMap(int i, float f) {
        String key, value;
        Map<String, String> map = new HashMap<>(i, f);
        long start = System.nanoTime();
        for (int i1 = 0; i1 < 10_000; i1++) {
            key = i1 + "";
            value = "aa";
            map.put(key, value);
        }
        long end = System.nanoTime();

        System.out.println("tim3" + (end - start));
        return end - start;
    }


}
