package org.clxmm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author clx
 * @date 2020-09-03 20:48
 */
public class Test3Map {


    public static void main(String[] args) {

        Map<String, Map> stuMap = new HashMap<>();
        Map<String, String> stu1Map = new HashMap<>();

        stu1Map.put("yuwen", "80");
        stu1Map.put("sx", "20");
        stu1Map.put("xy", "10");

        Map<String, String> stu2Map = new HashMap<>();

        stu2Map.put("yuwen", "81");
        stu2Map.put("sx", "21");
        stu2Map.put("xy", "11");

        Map<String, String> stu3Map = new HashMap<>();

        stu3Map.put("yuwen", "82");
        stu3Map.put("sx", "22");
        stu3Map.put("xy", "12");


        stuMap.put("zs1", stu1Map);
        stuMap.put("zs2", stu1Map);
        stuMap.put("zs3", stu1Map);


        System.out.println(stuMap);


    }

}
