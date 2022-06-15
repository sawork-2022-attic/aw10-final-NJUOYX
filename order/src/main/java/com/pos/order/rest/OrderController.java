package com.pos.order.rest;

import com.pos.cart.model.Cart;
import com.pos.database.model.Status;
import com.pos.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public Flux<Status> getAllStatus(@RequestBody String uid){
        return orderService.getAllStatus(uid);
    }

    @Deprecated
    @PostMapping("/order/new")
    public Mono<Boolean> newStatus(@RequestBody Cart cart){
        return orderService.newStatus(cart);
    }
}
