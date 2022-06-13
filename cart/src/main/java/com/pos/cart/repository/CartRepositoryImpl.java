package com.pos.cart.repository;

import com.pos.cart.model.Cart;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class CartRepositoryImpl implements CartRepository{

    private Cart cart;

    @Override
    public Cart getCart() {
        return Objects.requireNonNullElseGet(cart, Cart::new);
    }

    @Override
    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
