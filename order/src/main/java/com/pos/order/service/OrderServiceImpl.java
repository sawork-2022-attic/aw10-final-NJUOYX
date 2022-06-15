package com.pos.order.service;

import com.pos.cart.model.Cart;
import com.pos.database.model.Status;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.HashSet;
import java.util.function.Supplier;


@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8001").build();

    @Override
    public Flux<Status> getAllStatus() {
        return webClient.get()
                .uri("/database/status/all")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Status.class);
    }

    @RabbitListener(queues = "order")
    public void listen(Cart cart){
        Mono<Boolean> res = newStatus(cart);
        System.out.println("received a cart: " + res.block());
    }


    @Override
    public Mono<Boolean> newStatus(Status status) {
        return webClient.post()
                .uri("/database/status/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(status), Status.class)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Boolean.class);
    }

    @Override
    public Mono<Boolean> newStatus(Cart cart) {
        return newStatus(new Status(new HashSet<>(cart.getItems()), Instant.now()));
    }

}
