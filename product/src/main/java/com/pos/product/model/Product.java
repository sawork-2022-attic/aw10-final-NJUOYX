package com.pos.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String asin;
    private String image_url;
    private String image_url_high_res;
    private String main_cart;
    private double price;
    private String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return asin.equals(product.asin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asin);
    }
}
