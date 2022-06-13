package com.pos.product.service;

import com.pos.product.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> products();
    Product getProduct(String asin);
}
