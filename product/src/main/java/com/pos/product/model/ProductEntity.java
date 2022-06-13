package com.pos.product.model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "asin", nullable = false, length = 16)
    private String asin;

    @Column(name = "image_url", nullable = false, length = 1024)
    private String imageUrl;

    @Column(name = "image_url_high_res", nullable = false, length = 1024)
    private String imageUrlHighRes;

    @Column(name = "main_cat", nullable = false, length = 512)
    private String mainCat;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "title", nullable = false, length = 128)
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrlHighRes() {
        return imageUrlHighRes;
    }

    public void setImageUrlHighRes(String imageUrlHighRes) {
        this.imageUrlHighRes = imageUrlHighRes;
    }

    public String getMainCat() {
        return mainCat;
    }

    public void setMainCat(String mainCat) {
        this.mainCat = mainCat;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static ProductEntity fromAsin(String asin){
        ProductEntity product = new ProductEntity();
        product.setAsin(asin);
        return product;
    }
}