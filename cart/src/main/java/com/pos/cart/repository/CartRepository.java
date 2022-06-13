package com.pos.cart.repository;

import com.pos.cart.model.Cart;

public interface CartRepository {
    Cart getCart();

    void setCart(Cart cart);
}
