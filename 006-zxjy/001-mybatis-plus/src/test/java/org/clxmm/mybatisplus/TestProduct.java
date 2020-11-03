package org.clxmm.mybatisplus;

import org.clxmm.mybatisplus.entity.Product;
import org.clxmm.mybatisplus.mapper.ProductMapper;
import org.clxmm.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/11/2 10:46 下午
 */
@SpringBootTest
public class TestProduct {

    @Autowired
    private ProductMapper productMapper;


    @Test
    public void test() {
        Product p1 = productMapper.selectById(1L);


        Product p2 = productMapper.selectById(1L);

        p1.setPrice(p1.getPrice() + 50);

        productMapper.updateById(p1);


        p2.setPrice(p2.getPrice() - 30);
        int i = productMapper.updateById(p2);

        if (0 == i) {
            System.out.println("p2 更新失败");
            // 重试
            p2 = productMapper.selectById(1L);
            p2.setPrice(p2.getPrice() - 30);
            productMapper.updateById(p2);

        }

        System.out.println(productMapper.selectById(1L).getPrice());
    }

}
