package com.pos.database.model;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "quantity")
    private Integer quantity;

    public ItemEntity(){

    }

    public ItemEntity(ProductEntity productEntity, Integer quantity){
        this.product = productEntity;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Item toItem(){
        return new Item(product.toProduct(), quantity);
    }

    public static ItemEntity fromItem(Item item){
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setProduct(ProductEntity.fromProduct(item.getProduct()));
        itemEntity.setQuantity(item.getQuantity());
        return itemEntity;
    }
}