package com.springboot.blog.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.springboot.blog.entity.Category;

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
}
