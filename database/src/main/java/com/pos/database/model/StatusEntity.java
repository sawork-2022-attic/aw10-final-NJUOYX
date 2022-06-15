package com.pos.database.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "status")
public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "uid", nullable = false)
    private String uid;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cart",
            joinColumns = @JoinColumn(name = "status_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<ItemEntity> items = new LinkedHashSet<>();

    public StatusEntity(String uid, Set<ItemEntity> items, Instant createdAt){
        this.uid = uid;
        this.items = items;
        this.createdAt = createdAt;
    }

    public StatusEntity() {

    }

    public static StatusEntity fromUid(String uid){
        StatusEntity entity = new StatusEntity();
        entity.setUid(uid);
        return entity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Set<ItemEntity> getItems() {
        return items;
    }

    public void setItems(Set<ItemEntity> items) {
        this.items = items;
    }

    public Status toStatus(){
        return new Status(uid,
                items.stream()
                        .map(ItemEntity::toItem)
                        .collect(Collectors.toSet()),
                createdAt);
    }

    public static StatusEntity fromStatus(Status status){
        return new StatusEntity(
                status.getUid(),
                status.getItems().stream()
                        .map(ItemEntity::fromItem)
                        .collect(Collectors.toSet()),
                status.getCreatedAt());
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}