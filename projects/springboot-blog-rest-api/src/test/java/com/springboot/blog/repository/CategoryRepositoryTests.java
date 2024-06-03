package com.springboot.blog.repository;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.springboot.blog.entity.Category;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;


@DataJpaTest
public class CategoryRepositoryTests {
    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    @DisplayName("Test Saved Category")
    public void giveObjectCategory_whenSavedCategory_thenReturnObjectCategory(){
        //given
        Category category = Category.builder()
        .name("Category one")
        .description("Category one description")
        .build();
        
        //when
        Category categorySaved = this.categoryRepository.save(category);

        //then
        Assertions.assertThat(categorySaved).isNotNull();
        Assertions.assertThat(categorySaved.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("List Category")
    public void giveObjectListCategory_whenFindAllCategory_thenReturnListObjectCategory(){
        //given
        Category category = Category.builder()
        .name("Category one")
        .description("Category one description")
        .build();
        this.categoryRepository.save(category);

        Category categoryTwo = Category.builder()
        .name("Category two")
        .description("Category one description")
        .build();
        this.categoryRepository.save(categoryTwo);
        
        //when
        List<Category> categorys = this.categoryRepository.findAll();
      

        //then
        Assertions.assertThat(categorys).isNotNull();
        Assertions.assertThat(categorys.size()).isGreaterThan(0);
    }
}
