package com.pos.product.controller;

import com.pos.product.model.Product;
import com.pos.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{asin}")
    public Mono<Product> getProduct(@PathVariable String asin){
        return Mono.justOrEmpty(productService.getProduct(asin));
    }

    @GetMapping("/")
    public Flux<Product> getAllProducts(){
        return Flux.fromIterable(productService.products());
    }
}
