package com.pos.database.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class CartEntity {
    @EmbeddedId
    private CartEntityId id;

    public CartEntityId getId() {
        return id;
    }

    public void setId(CartEntityId id) {
        this.id = id;
    }

//TODO [JPA Buddy] generate columns from DB
}