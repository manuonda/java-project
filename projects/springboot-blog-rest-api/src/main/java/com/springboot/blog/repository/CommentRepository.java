package com.springboot.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.blog.entity.Comment;

/**
 * Comment Repository
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

   List<Comment> findByPostId(Long id);
}
