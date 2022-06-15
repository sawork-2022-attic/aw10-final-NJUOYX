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

    @GetMapping("/database/product/all")
    public Flux<Product> getAllProducts(){
        return databaseService.getAllProduct();
    }

    @GetMapping("/database/product/{asin}")
    public Mono<Product> getProduct(@PathVariable String asin){
        return databaseService.getProduct(asin);
    }

    @PostMapping("/database/status/all")
    public Flux<Status> getAllStatus(@RequestBody String uid){
        return databaseService.getAllStatus(uid);
    }

    @PostMapping("/database/status/add")
    public Mono<Boolean> addStatus(@RequestBody Status status){
        return databaseService.addStatus(status);
    }
}
