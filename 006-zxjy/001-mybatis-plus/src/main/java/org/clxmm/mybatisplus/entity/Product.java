package org.clxmm.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/11/2 10:45 下午
 */
@Data
public class Product {

    private Long id;

    private String name;

    private Integer price;

    @Version
    private Integer version;
}
