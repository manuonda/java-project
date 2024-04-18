package com.springboot.blog.controller;

import static org.mockito.ArgumentMatchers.any;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.CoreMatchers.is;


import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.service.PostService;

@WebMvcTest(PostController.class)
public class PostControllerTests  {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Autowired
    private ObjectMapper objectMapper;



    @Test
    @DisplayName("Test Junit Method Save Post")
    public void givenObjectUsuarioDTO_whenSavePost_thenReturnObjectPostDTO() throws Exception{
        //given
        PostDTO dto  = new PostDTO();
        dto.setTitle("title one");
        dto.setContent("content one");
        dto.setDescription("description one");
        
        BDDMockito.given(this.postService.createPost(any(PostDTO.class)))
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
    



}
