package com.pos.cart.model;

import com.pos.database.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RedisHash(value = "cart")
public class Cart implements Serializable {
    @Id
    private String uid;

    public Cart(){

    }

    public Cart(String uid){
        this.uid = uid;
    }

    public Cart(String uid, List<Item> items){
        this.uid = uid;
        this.items = items;
    }

    private List<Item> items = new ArrayList<>();

    public boolean addItem(Item item) {
        return items.add(item);
    }

    public double total() {
        double total = 0;
        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).getQuantity() * items.get(i).getProduct().getPrice();
        }
        return total;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items){
        this.items = items;
    }
}
