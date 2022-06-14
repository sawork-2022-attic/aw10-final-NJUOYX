package com.pos.cart.service;

import com.pos.cart.model.Cart;
import com.pos.database.model.Item;
import reactor.core.publisher.Mono;

public interface CartService {
    Cart getCart();

    boolean addItem(Item item);

    double getTotal();

    Mono<Boolean> checkout();
}
