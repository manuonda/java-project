package com.springboot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.entity.Comment;

/**
 * Comment Repository
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
