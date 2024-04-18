package com.springboot.blog.service;


import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.EntityFoundException;
import com.springboot.blog.exception.EntityNotFoundException;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.payload.PostResponseDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

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

        BDDMockito.when(this.postRepository.findByTitle(postDTO.getTitle())).thenReturn(Optional.empty());
        BDDMockito.when(this.postRepository.save(Mockito.any(Post.class))).thenReturn(post);

        //when
        PostDTO postDtoSaved = this.postService.createPost(postDTO);

        //then
        Assertions.assertThat(postDtoSaved).isNotNull();
        Mockito.verify(postRepository, Mockito.times(1)).save(post);
    }


    @Test
    @DisplayName("Test Junit Method Save Post, Error exist title")
    public void givenObjectPost_whenSaveObjectPost_thenReturnError(){
        //given
        Post post = new Post();
        post.setTitle("title one");
        post.setContent("Content one");
        post.setDescription("Description one");

        PostDTO postDTO = new PostDTO();
        postDTO.setTitle("Title ");
        postDTO.setContent("Content information");
        postDTO.setDescription("Description infomration ");


        BDDMockito.when(this.postRepository.findByTitle(postDTO.getTitle())).thenReturn(Optional.of(post));
        
        
        org.junit.jupiter.api.Assertions.assertThrows(EntityFoundException.class , () -> {
           this.postService.createPost(postDTO);            
        });
        
        Mockito.verify(postRepository, Mockito.never()).save(BDDMockito.any(Post.class));
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

        Page<Post> mockPagePost = new PageImpl<>(listado);
        BDDMockito.when(this.postRepository.findAll(any(Pageable.class))).thenReturn(mockPagePost);
       
         
        //when
        PostResponseDTO result = this.postService.getAllPosts(0,10,"id","ASC");


        //then
        Assertions.assertThat(result.totalElements()).isEqualTo(2);
        Assertions.assertThat(result.pageNo()).isZero();
    }    


    @Test
    @DisplayName("Test Junit Method list empty")
    public void givenEmptyList_whenFindAll_thenReturnListEmpty(){
        
        List<Post> listado = Collections.emptyList();

        Page<Post> mockPagePost = new PageImpl<>(listado);
        BDDMockito.when(this.postRepository.findAll(any(Pageable.class))).thenReturn(mockPagePost);
       
         
        //when
        PostResponseDTO result = this.postService.getAllPosts(0,10,"id","ASC");


        //then
        Assertions.assertThat(result.totalElements()).isEqualTo(0);
        Assertions.assertThat(result.pageNo()).isZero();

    }

    @Test
    @DisplayName("Test Junit Method get By Id")
    public void givenObjectPost_whenFindById_thenReturnObjectPost(){
        
        //given
        Long id = 1L;
        Post post = Post.builder().title("title one").content("content one").description("description one").build();
        BDDMockito.when(this.postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        
        //when 
        PostDTO postDTO = this.postService.findById(id);

        //then
        Assertions.assertThat(postDTO).isNotNull();
    }


    @Test
    @DisplayName("Test Junit Method not find By Id")
    public void givenIdLong_whenFindById_thenReturnErrorNotFind(){
        //given
        Long id = 1L;
        when(this.postRepository.findById(id)).thenReturn(Optional.empty());

       org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () ->{
          this.postService.findById(id);
       },"Not Found Post by Id");

       verify(postRepository, times(1)).findById(id);
        
    }

    @Test
    @DisplayName("Test Junit Method delete by Id")
    public void givenObjectPost_whenDeleteById_thenEmpyObject(){

        //given
        Post post = Post.builder()
                    .id(1L) 
                    .title("Title one").content("Content one")
                    .description("Description one").build();
         
        //when(this.postRepository.save(post)).thenReturn(post);
        when(this.postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        
        //when 
        this.postService.deleteById(post.getId());

        //then
        verify(postRepository, times(1)).delete(post);

    }

    @Test
    @DisplayName("Test Delete by Id Not Found")
    public void givenObjectEmpty_whenDeleteById_thenErrorNotFoundById(){
      
      //given 
       when(this.postRepository.findById(anyLong())).thenReturn(Optional.empty()); 
      //when 
      org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
         this.postService.deleteById(anyLong());
      });
      
      //then
      verify(postRepository, never()).deleteById(anyLong());
    }

    @Test
    @DisplayName("Test Update Post find By Id")
    public void  givenObjectPost_whenUpdatePost_thenReturnObjectPost(){
       //given
       Long id = 1L;
       Post post = Post.builder()
       .id(id).title("Title one")
       .content("Content one")
       .description("Description one")
       .build();

       PostDTO postDTO = new PostDTO();
       postDTO.setContent("Contenido one");
       postDTO.setTitle("Title one");
       postDTO.setDescription("Description one");

        when(this.postRepository.findById(id)).thenReturn(Optional.of(post));
        when(this.postRepository.findByTitle(anyString())).thenReturn(Optional.empty());

       //when 
       PostDTO postUpdate = this.postService.updatePost(postDTO, id);

       //then
       Assertions.assertThat(postUpdate).isNotNull();
    }



    @Test
    @DisplayName("Test Update Post find Title Exists")
    public void  givenObjectPost_whenUpdatePost_thenReturnEmptyErrorTitleExists(){
       //given
       Long id = 1L;
       // post parameter
       PostDTO postDTO = new PostDTO();
       postDTO.setId(id);
       postDTO.setContent("Updated Title");
       postDTO.setTitle("Title one");
       postDTO.setDescription("Description one");

       Post post = Post.builder()
       .id(id).title("Original title")
       .content("Content one")
       .description("Description one")
       .build();

        when(this.postRepository.findById(id)).thenReturn(Optional.of(post));
        
        // Assuming there's another post with the same title
        Post postExisting  = Post.builder()
        .id(2L)
        .title("Updated Title")
        .content("Content Title")
        .description("Description Title")
        .build();    

        when(this.postRepository.findByTitle(postDTO.getTitle())).thenReturn(Optional.of(postExisting));

       //when 
       org.junit.jupiter.api.Assertions.assertThrows(EntityFoundException.class, () -> {
           this.postService.updatePost(postDTO, id);
       });

       //then
       verify(postRepository, never()).save(post);
    }
}
