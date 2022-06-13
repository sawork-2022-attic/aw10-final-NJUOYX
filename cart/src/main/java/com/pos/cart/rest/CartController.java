package com.pos.cart.rest;

import com.pos.cart.model.Cart;
import com.pos.cart.model.Item;
import com.pos.cart.service.CartService;
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

    @GetMapping("/")
    public Mono<Cart> getCart(){
        return Mono.just(cartService.getCart());
    }

    @PostMapping("/add")
    public Mono<Boolean> addItem(@RequestBody Item item){
        return Mono.just(cartService.addItem(item));
    }

    @GetMapping("/total")
    public Mono<Double> getTotal(){
        return Mono.just(cartService.getTotal());
    }

    @PostMapping("/checkout")
    public Mono<Boolean> checkout(){
        return cartService.checkout();
    }
}
