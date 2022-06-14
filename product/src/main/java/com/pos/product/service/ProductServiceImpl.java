package com.pos.product.service;

import com.pos.database.model.Product;
import com.pos.database.model.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class ProductServiceImpl implements ProductService{

    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8010").build();

    @Override
    public Flux<Product> products() {
        return webClient.get()
                .uri("/product/all")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Product.class);
    }

    @Override
    public Mono<Product> getProduct(String asin) {
        return webClient.get()
                .uri("/product/{asin}", asin)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Product.class);
    }
}
