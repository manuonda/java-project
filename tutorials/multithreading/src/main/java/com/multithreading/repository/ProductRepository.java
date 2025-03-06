package com.multithreading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multithreading.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

}
