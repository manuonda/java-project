package com.springboot.blog.controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.springboot.blog.service.impl.PostServiceImpl;

@WebMvcTest(PostController.class)
public class PostControllerTests {


    @Mock
    private PostServiceImpl   postServiceImpl;


}
