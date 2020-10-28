package org.clxmm.springboot02amqp.bean;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/28 8:05 下午
 */
public class Book {


    private String name;

    private Long price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
