package com.pos.product.rest;

import com.pos.database.model.Product;
import com.pos.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@EnableEurekaClient
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("product/{asin}")
    public Mono<Product> getProduct(@PathVariable String asin){
        return productService.getProduct(asin);
    }

    @GetMapping("/product")
    public Flux<Product> getAllProducts(){
        return productService.products();
    }
}
