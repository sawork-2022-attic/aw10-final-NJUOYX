package com.pos.cart.service;

import com.pos.cart.repository.CartRepository;
import com.pos.cart.model.Cart;
import com.pos.database.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CartServiceImpl implements CartService{

    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8004").build();

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart getCart() {
        return cartRepository.getCart();
    }

    @Override
    public boolean addItem(Item item) {
        Cart cart = cartRepository.getCart();
        Item it = cart.getItems().stream()
                .filter(i -> i.equals(item))
                .findFirst().orElse(null);
        if(it == null){
            boolean res = cart.addItem(item);
            cartRepository.setCart(cart);
            return res;
        }else{
            it.addQuantity(1);
            return true;
        }
    }

    @Override
    public double getTotal() {
        return cartRepository.getCart().total();
    }

    @Override
    public Mono<Boolean> checkout() {
        Mono<Boolean> res = webClient.post()
                .uri("/new")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(cartRepository.getCart()), Cart.class)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Boolean.class);
        cartRepository.setCart(new Cart());
        return res;
    }
}
