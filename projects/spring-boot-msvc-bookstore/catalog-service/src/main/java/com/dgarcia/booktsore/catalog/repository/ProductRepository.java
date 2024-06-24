package com.dgarcia.booktsore.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dgarcia.booktsore.catalog.entity.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
