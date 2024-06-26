package com.springboot.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    Optional<Category> findByName(String name);
}
