package com.springboot.blog.service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.payload.PostResponseDTO;

import java.util.List;

public interface PostService {

    /**
     * Funcion que permite crear
     * postDTO
     * @param postDTO
     * @return
     */
    PostDTO createPost(PostDTO postDTO);

    /**
     * Get All Post
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return
     */
    PostResponseDTO getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

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
