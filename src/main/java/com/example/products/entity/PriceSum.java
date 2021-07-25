package com.example.products.entity;


import java.math.BigDecimal;

public class PriceSum {

    private BigDecimal price;


    public PriceSum() {
        this.price = new BigDecimal(0.00);
    }

    public PriceSum(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal add(BigDecimal valueToAdd){
        return this.price.add(valueToAdd);
    }

    public BigDecimal subtract(BigDecimal valueToSubtract){
        return this.price.subtract(valueToSubtract);
    }

    public BigDecimal getPrice() {
        return price;
    }
}
