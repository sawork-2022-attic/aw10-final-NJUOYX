package com.pos.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
