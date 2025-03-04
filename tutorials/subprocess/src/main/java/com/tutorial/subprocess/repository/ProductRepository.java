package com.tutorial.subprocess.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.subprocess.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
