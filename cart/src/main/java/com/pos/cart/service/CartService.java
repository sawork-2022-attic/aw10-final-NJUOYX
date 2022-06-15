package com.pos.cart.service;

import com.pos.cart.model.Cart;
import com.pos.database.model.Item;
import reactor.core.publisher.Mono;

public interface CartService {
    Mono<Cart> getCart(String uid);

    Mono<Boolean> addItem(String uid, Item item);

    Mono<Double> getTotal(String uid);

    Mono<Boolean> checkout(String uid);
}
