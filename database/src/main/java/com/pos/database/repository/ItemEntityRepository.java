package com.pos.database.repository;

import com.pos.database.model.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemEntityRepository extends JpaRepository<ItemEntity, Integer> {
}