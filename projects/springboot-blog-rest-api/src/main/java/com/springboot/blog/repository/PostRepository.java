package com.springboot.blog.repository;

import com.springboot.blog.entity.Post;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM post p where p.title =:title ")
    Optional<Post> findByTitle(@PathParam("title") String title);

}
