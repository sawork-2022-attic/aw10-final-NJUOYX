package com.pos.database.service;

import com.pos.database.model.Product;
import com.pos.database.model.Status;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface DatabaseService {
    Flux<Product> getAllProduct();

    Mono<Product> getProduct(String asin);

    Flux<Status> getAllStatus(String uid);

    Mono<Boolean> addStatus(Status status);
}
