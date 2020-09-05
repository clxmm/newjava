package org.clxmm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author clx
 * @date 2020-09-01 20:56
 */
public class TestMap {

    public static void main(String[] args) {
        System.out.println("first===");


        // hashmap 的基本用法
        // 创建hashMap 对象
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("张三", new Integer(120));
        userMap.put("张三1", new Integer(121));
        Object age = userMap.get("张三");
        System.out.println(age);


        System.out.println("------------------");
        // 例题1
        Map<String, Integer> testMap = new HashMap<>();
        testMap.put("zhan1", 90);
        testMap.put("zhan2", 93);
        testMap.put("zhan3", 93);
        testMap.put("zhan4", 92);
        testMap.put("zhan5", 91);

        System.out.println(testMap.get("zhan2"));

        // 注意输出的顺序   取址与hash
        System.out.println(testMap);





    }


}
