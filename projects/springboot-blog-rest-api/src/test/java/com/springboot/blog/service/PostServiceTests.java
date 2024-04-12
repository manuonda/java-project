package com.springboot.blog.service;


import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.impl.PostServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PostServiceTests {

    @Mock
    private PostRepository  postRepository;


    @InjectMocks
    private PostServiceImpl postService;

    @BeforeEach
    private void setUp(){

    }

    @Test
    @DisplayName("Test Junit Method Save Post")
    public void givenObjectPost_whenSaveObjectPost_thenReturnObjectPost(){
        //given
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle("Title ");
        postDTO.setContent("Content information");
        postDTO.setDescription("Description infomration ");

        Post post = postService.mapToEntity(postDTO);

        BDDMockito.when(this.postRepository.save(Mockito.any(Post.class))).thenReturn(post);

        //when
        PostDTO postDtoSaved = this.postService.createPost(postDTO);

        //then
        Assertions.assertThat(postDtoSaved).isNotNull();
        Mockito.verify(postRepository, Mockito.times(1)).save(post);
    }


    @Test
    @DisplayName("Test Junit Method get all Posts")
    public void givenListObjectPost_whenReturnListPost_returnListObjectPost(){
       //given
        Post post = Post.builder()
                .id(1L)
                .title("Title 1")
                .content("Content 1")
                .description("Content 1")
                .build();
        Post postTwo = Post.builder()
                .id(2L)
                .title("Title 2")
                .content("Content 2")
                .description("Description 2")
                .build();
        List<Post> listado = List.of(post, postTwo);

        BDDMockito.when(this.postRepository.findAll()).thenReturn(listado);

        //when
        List<PostDTO> listadoFindAll = this.postService.getAllPosts();

        //then
        Assertions.assertThat(listadoFindAll).isNotEmpty();
        Assertions.assertThat(listadoFindAll.size()).isEqualTo(2);

        Assertions.assertThat(listadoFindAll.get(0).getId()).isEqualTo(1L);
        Assertions.assertThat(listadoFindAll.get(0).getTitle()).contains("Title 1");

        Mockito.verify(postRepository, Mockito.times(1)).findAll();
    }


    @Test
    @DisplayName("Test Junit Method get By Id")
    public void given_when_then(){
        
    }

}
