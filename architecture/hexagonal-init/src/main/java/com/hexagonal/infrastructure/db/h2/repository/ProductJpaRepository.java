package com.hexagonal.infrastructure.db.h2.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexagonal.infrastructure.db.h2.entity.ProductEntity;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {

    Optional<ProductEntity> findProductByProductId(String productId);
}
