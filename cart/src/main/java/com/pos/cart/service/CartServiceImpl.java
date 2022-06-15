package com.pos.cart.service;

import com.pos.cart.repository.CartRepository;
import com.pos.cart.model.Cart;
import com.pos.database.model.Item;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.concurrent.Callable;


@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    private Cart _getCart(String uid){
        return cartRepository.findById(uid).orElse(new Cart(uid));
    }

    private void _deleteCart(String uid){
        cartRepository.deleteById(uid);
    }

    private Callable<Cart> getCartWay(String uid){
        return () -> _getCart(uid);
    }

    private Runnable addItemWay(String uid, Item item){
        return ()->{
            Cart cart = _getCart(uid);
            Optional<Item> it = cart.getItems().stream()
                    .filter(i -> i.equals(item))
                    .findFirst();
            if(it.isPresent()){
                it.get().addQuantity(item.getQuantity());
            }else{
                cart.getItems().add(item);
            }
            cartRepository.save(cart);
        };
    }

    private Runnable checkoutWay(String uid){
        return () -> {
            _deleteCart(uid);
        };
    }

    @Override
    public Mono<Cart> getCart(String uid) {
       return Mono.fromCallable(getCartWay(uid));
    }

    @Override
    public Mono<Boolean> addItem(String uid, Item item) {
        return Mono.fromRunnable(addItemWay(uid, item));
    }

    @Override
    public Mono<Double> getTotal(String uid) {
        return getCart(uid).flatMap(cart-> Mono.just(cart.total()));
    }

    @Override
    public Mono<Boolean> checkout(String uid) {
        return Mono.fromRunnable(checkoutWay(uid));
    }

}
