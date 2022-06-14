package com.pos.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {
    private Product product;
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return product.equals(item.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }

    public Item addQuantity(int inc){
        quantity = quantity + inc;
        return this;
    }
}
