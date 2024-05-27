package com.springboot.blog.repository;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.springboot.blog.entity.Category;


@DataJpaTest
public class CategoryTest {

    @Autowired
    private CategoryRepository categoryRepository;


    Category category;

    @BeforeEach
    public void setUp(){
      category = Category.builder()
      .name("Category number1")
      .description("Descriptino Category number 1")
      .build();
    }


    @Test
    @DisplayName("Test Method Save Category")
    public void givenObjectCategory_whenSaveCategory_thenReturnObjectCategory(){
      //given
      Cageg 
      //when
      //then

    }
    


}
