package com.pos.database.repository;

import com.pos.database.model.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusEntityRepository extends JpaRepository<StatusEntity, Integer> {
}