package com.springboot.blog.service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDTO;

import java.util.List;

public interface PostService {

    /**
     * Funcion que permite crear
     * @param postDTO
     * @return
     */
    PostDTO createPost(PostDTO postDTO);
    List<PostDTO> getAllPosts();

    /**
     * Update Post
     * @param postDTO
     * @param id
     * @return
     */
    PostDTO updatePost(PostDTO postDTO, Long id);

    /**
     * Find By Id PostDTO
     * @param id
     * @return
     */
    PostDTO findById(Long id);

    /**
     * Delete By Id
     * @param id
     */
    void deleteById(Long id);

}
