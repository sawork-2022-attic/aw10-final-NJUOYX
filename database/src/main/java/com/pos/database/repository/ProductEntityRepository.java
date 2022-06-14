package com.pos.database.repository;

import com.pos.database.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Integer> {
}