package com.example.products.rest;


import com.example.products.dao.ProductDAO;
import com.example.products.entity.PriceSum;
import com.example.products.entity.Product_x;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductRestController {


    ProductDAO productDAO;

    @Autowired
    public ProductRestController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping("/checkout")
    List<Product_x> simpleCheckout(){
        return productDAO.findAll();
    }

    @PostMapping("/checkoutPrice")
    PriceSum checkout(@RequestBody List<String> stringBody){

        List<Integer> integerBody = stringBody.stream().map(Integer::parseInt).collect(Collectors.toList());
        PriceSum sum = productDAO.getPriceByIds(integerBody);

        return sum;
    }




}
