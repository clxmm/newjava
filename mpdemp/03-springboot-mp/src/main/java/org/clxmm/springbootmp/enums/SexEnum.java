package org.clxmm.springbootmp.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/1/26 8:09 下午
 */
public enum SexEnum implements IEnum<Integer> {
    MAN(1,"男"),
    WOMAN(2,"女");
    ;
    private int value;
    private String desc;

    SexEnum(int value, String desc) { this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }


    @Override
    public String toString() {
        return this.desc;
    }
}
