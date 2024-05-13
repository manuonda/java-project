package com.springboot.blog.service;

import java.util.List;

import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.payload.PostResponseDTO;


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


    /**
     * Return List Post by idCategory
     * @param id
     * @return
     */
    List<PostDTO> getByIdCategory(Long id);

}
