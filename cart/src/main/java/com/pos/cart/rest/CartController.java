package com.pos.cart.rest;

import com.pos.cart.model.User;
import com.pos.cart.model.UserItem;
import com.pos.cart.service.CartService;
import com.pos.cart.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
    public Mono<Cart> getCart(@RequestBody User uid){
        return cartService.getCart(uid.getUid());
    }

    @PostMapping("/cart/add")
    public Mono<Boolean> addItem(@RequestBody UserItem useritem){
        return cartService.addItem(useritem.getUid(), useritem.getItem());
    }
    @PostMapping("/cart/total")
    public Mono<Double> getTotal(@RequestBody User uid){
        return cartService.getTotal(uid.getUid());
    }

    @PostMapping("/cart/checkout")
    public Mono<Boolean> checkout(@RequestBody User uid){
        return cartService.checkout(uid.getUid());
    }
}
