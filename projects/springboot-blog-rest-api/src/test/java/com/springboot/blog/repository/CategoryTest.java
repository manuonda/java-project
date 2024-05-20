package com.springboot.blog.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class CategoryTest {

    @Autowired
    private CategoryRepository categoryRepository;


    


}
