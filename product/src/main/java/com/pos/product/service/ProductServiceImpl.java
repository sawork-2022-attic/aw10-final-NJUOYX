package com.pos.product.service;

import com.pos.database.model.Product;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class ProductServiceImpl implements ProductService{

    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8001").build();

    @Override
    public Flux<Product> products() {
        return webClient.get()
                .uri("/database/product/all")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Product.class);
    }

    @Override
    public Mono<Product> getProduct(String asin) {
        return webClient.get()
                .uri("/database/product/{asin}", asin)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Product.class);
    }
}
