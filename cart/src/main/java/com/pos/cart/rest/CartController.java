package com.pos.cart.rest;

import com.pos.cart.service.CartService;
import com.pos.cart.model.Cart;
import com.pos.database.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public Mono<Cart> getCart(){
        return Mono.just(cartService.getCart());
    }

    @PostMapping("/cart/add")
    public Mono<Boolean> addItem(@RequestBody Item item){
        return Mono.just(cartService.addItem(item));
    }

    @GetMapping("/cart/total")
    public Mono<Double> getTotal(){
        return Mono.just(cartService.getTotal());
    }

    @PostMapping("/cart/checkout")
    public Mono<Boolean> checkout(){
        return cartService.checkout();
    }
}
