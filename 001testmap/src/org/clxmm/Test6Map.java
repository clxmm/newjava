package org.clxmm;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. isEmpty 判断是否为空
 * 2.  remove 删除节点
 * 3. clear 清空
 * 4 . 判断是否存在 key/value  containsKey/containsValue
 * 5. 替换  replace
 * 6. 如果key不存在 则添加
 * @author clx
 * @date 2020-09-05 21:08
 */
public class Test6Map {


    public static void main(String[] args) {
        Map<String, String> map = new HashMap();
        map.put("x", "123");
        map.put("y", "456");


        System.out.println("is null ? " + map.isEmpty());

        // 删除节点
//        map.remove("x");
//        map.remove("y","123");
//        map.clear();


        System.out.println(map.containsKey("x"));
        System.out.println(map.containsValue("123"));


        // 替换

        map.replace("x","456");
        map.replace("y","456","123");

        map.put("x","789");

        map.putIfAbsent("x","111");


        System.out.println(map);
    }


}
