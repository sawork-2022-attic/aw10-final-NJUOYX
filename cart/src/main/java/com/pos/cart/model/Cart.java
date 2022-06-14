package com.pos.cart.model;

import com.pos.database.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class Cart implements Serializable {
    private final List<Item> items = new ArrayList<>();


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
}
