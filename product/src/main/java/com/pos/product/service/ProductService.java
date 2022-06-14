package com.pos.product.service;

import com.pos.database.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<Product> products();
    Mono<Product> getProduct(String asin);
}
