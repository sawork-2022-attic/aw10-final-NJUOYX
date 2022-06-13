package com.pos.product.repository;

import com.pos.product.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Integer> {
}