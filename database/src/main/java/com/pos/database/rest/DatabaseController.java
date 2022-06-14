package com.pos.database.rest;

import com.pos.database.model.Product;
import com.pos.database.model.Status;
import com.pos.database.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class DatabaseController {

    @Autowired
    private DatabaseService databaseService;

    @GetMapping("/product/all")
    public Flux<Product> getAllProducts(){
        return Flux.fromIterable(databaseService.getAllProduct());
    }

    @GetMapping("/product/{asin}")
    public Mono<Product> getProduct(@PathVariable String asin){
        return Mono.just(databaseService.getProduct(asin));
    }

    @GetMapping("/status/all")
    public Flux<Status> getAllStatus(){
        return Flux.fromIterable(databaseService.getAllStatus());
    }

    @PostMapping("/status/add")
    public Mono<Boolean> addStatus(@RequestBody Status status){
        return Mono.just(databaseService.addStatus(status));
    }
}
