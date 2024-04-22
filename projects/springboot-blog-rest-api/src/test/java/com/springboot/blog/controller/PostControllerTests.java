package com.springboot.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PostController.class)
class PostControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }


    @org.junit.Test
    @DisplayName("Test Junit Method Save Post")
    public void givenObjectUsuarioDTO_whenSavePost_thenReturnObjectPostDTO() throws Exception{
        //given
        PostDTO dto  = new PostDTO();
        dto.setTitle("title one");
        dto.setContent("content one");
        dto.setDescription("description one");

        BDDMockito.given(postService.createPost(any(PostDTO.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/v1/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));

        //then
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.title", is("title one")))
                .andExpect(jsonPath("$.content", is("content one")));
    }

    
    @Test
    void getAllPosts() {
    }

    @Test
    void getPostById() {
    }

    @Test
    void createPost() {
    }

    @Test
    void updatePost() {
    }

    @Test
    void deletePost() {
    }
}