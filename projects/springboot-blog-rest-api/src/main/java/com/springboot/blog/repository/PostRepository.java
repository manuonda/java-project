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


    @Query(value = "SELECT * FROM posts p WHERE p.title LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    List<Post> searchPost(String  title);

    /*
     * Return Post by find By Id
     */
    Optional<Post> findById(Long id);

     /**
     * Returns the found list of post entries whose title and description is given
     * as a method parameters. If no post entries is found, this method
     * returns an empty list.
     */

    List<Post> findByTitleOrDescription(String title, String description);

      /**
     * Returns the found list of post entries whose title and description is given
     * as a method parameters. If no post entries is found, this method
     * returns an empty list.
     */
    List<Post> findByTitleAndDescription(String title, String description);


    Post findDistinctByTitle(String title);

    List<Post> findByTitleContaining(String title);

    List<Post> findByTitleLike(String title);


    List<Post> findByTitleIn(List<String> titles);


}
