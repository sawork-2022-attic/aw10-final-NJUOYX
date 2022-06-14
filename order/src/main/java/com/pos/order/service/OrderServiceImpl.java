package com.pos.order.service;

import com.pos.cart.model.Cart;
import com.pos.database.model.Status;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.HashSet;



@Service
public class OrderServiceImpl implements OrderService{

    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8001").build();

    @Override
    public Flux<Status> getAllStatus() {
        return webClient.get()
                .uri("/database/status/all")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Status.class);
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
