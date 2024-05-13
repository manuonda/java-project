package com.springboot.blog.repository;

import com.springboot.blog.entity.Post;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p where p.title = ?1% ")
    Optional<Post> findByTitle(String title);

    @Query("SELECT p FROM Post p where p.category.id= ?1%")
    List<Post> findByIdCategory(Long id);

}
