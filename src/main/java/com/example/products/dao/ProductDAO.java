package com.example.products.dao;


import com.example.products.entity.PriceSum;
import com.example.products.entity.Product_x;

import java.util.List;


public interface ProductDAO {


    public List<Product_x> findAll();

    public Product_x findById(int productId);

    public PriceSum getPriceByIds(List<Integer> idList);

    public void save(Product_x theProductX);

    public void deleteById(int productId);

}
