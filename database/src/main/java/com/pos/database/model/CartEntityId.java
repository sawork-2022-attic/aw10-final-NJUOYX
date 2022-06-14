package com.pos.database.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CartEntityId implements Serializable {
    private static final long serialVersionUID = -2215055949522890141L;
    @Column(name = "status_id", nullable = false)
    private Integer statusId;

    @Column(name = "item_id", nullable = false)
    private Integer itemId;

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CartEntityId entity = (CartEntityId) o;
        return Objects.equals(this.itemId, entity.itemId) &&
                Objects.equals(this.statusId, entity.statusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, statusId);
    }

}