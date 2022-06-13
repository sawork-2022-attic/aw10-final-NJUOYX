package com.pos.cart.service;

import com.pos.cart.model.Cart;
import com.pos.cart.model.Item;
import com.pos.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CartServiceImpl implements CartService{

    private final WebClient webClient = WebClient.builder().baseUrl("localhost:8004").build();

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart getCart() {
        return cartRepository.getCart();
    }

    @Override
    public boolean addItem(Item item) {
        Cart cart = cartRepository.getCart();
        boolean res = cart.addItem(item);
        cartRepository.setCart(cart);
        return res;
    }

    @Override
    public double getTotal() {
        return cartRepository.getCart().getTotal();
    }

    @Override
    public Mono<Boolean> checkout() {
        return webClient.get()
                .uri("/checkout")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Boolean.class);
    }
}
