package com.springboot.blog.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.springboot.blog.entity.Category;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;


@DataJpaTest
class CategoryRepositoryTest {

    
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
}