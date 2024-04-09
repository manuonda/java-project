package com.springboot.blog.service.impl;

import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {


    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        return null;
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return null;
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long id) {
        return null;
    }

    @Override
    public PostDTO findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }


}
