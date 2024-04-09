package com.springboot.blog.repository;


import com.springboot.blog.entity.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class PostTests {

    @Autowired
    private PostRepository postRepository;


    @Test
    @DisplayName("Test Junit Method Save Post")
    public void givenObjectPost_whenSavePost_thenReturnObjectPost(){
        //given
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Title one");
        post.setDescription("Descriptio One");
        post.setContent("Information");

        //when
        Post postSaved = this.postRepository.save(post);

        //then
        assertThat(postSaved).isNotNull();
        assertThat(postSaved.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test Jnit Method Get All Post")
    public void givenListObjectPost_whenGetAllPosts_thenReturnListObject(){
        //given
        Post post = Post.builder()
                .title("title one")
                .content("content one")
                .description("description one")
                .build();

        this.postRepository.save(post);
        Post post1 = Post.builder()
                .title("Title two")
                .content("Content Two")
                .description("Description Two")
                .build();
        this.postRepository.save(post1);

        //when
        List<Post> posts = this.postRepository.findAll();

        //then
        assertThat(posts).isNotNull();
        assertThat(posts.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Junit Method Test find By Id")
    public void givenObjectPost_whenFindById_thenReturnObjectPost(){
        //given
        Post post = Post.builder()
                .title("title one")
                .content("content one")
                .description("description one")
                .build();

        this.postRepository.save(post);
        //when
         Post postFind = this.postRepository.findById(post.getId()).get();

         //then
        assertThat(postFind).isNotNull();
        assertThat(postFind.getId()).isGreaterThan(0);
        assertThat(post.getTitle()).contains("title one");
    }
}
