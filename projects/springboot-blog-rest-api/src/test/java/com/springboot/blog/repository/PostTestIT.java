package com.springboot.blog.repository;


import com.springboot.blog.AbstractBaseContainer;
import com.springboot.blog.entity.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostTestIT extends AbstractBaseContainer {

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
}
