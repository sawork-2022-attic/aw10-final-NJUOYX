package com.pos.database.service;

import com.pos.database.model.Product;
import com.pos.database.model.Status;

import java.util.List;

public interface DatabaseService {
    List<Product> getAllProduct();

    Product getProduct(String asin);

    List<Status> getAllStatus();

    Boolean addStatus(Status status);
}
