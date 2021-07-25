package com.example.products.entity;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product_x")
public class Product_x {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "discount")
    private String discount;


    public Product_x() {
    }

    public Product_x(String name, BigDecimal unitPrice, String discount) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }


    public Product_x(int id, String name, BigDecimal unitPrice, String discount) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", discount='" + discount + '\'' +
                '}';
    }
}
