package com.pos.order.service;

import com.pos.cart.model.Cart;
import com.pos.database.model.Status;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface OrderService {
    Flux<Status> getAllStatus();

    Mono<Boolean> newStatus(Status status);

    Mono<Boolean> newStatus(Cart cart);
}
