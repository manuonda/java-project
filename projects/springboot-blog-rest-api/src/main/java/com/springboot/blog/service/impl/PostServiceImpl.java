package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.EntityFoundException;
import com.springboot.blog.exception.EntityNotFoundException;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {


    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {

        Optional<Post> findPost = this.postRepository.findByTitle(postDTO.getTitle());
        if ( findPost.isPresent()){
          throw new EntityFoundException("The Post title exist");
        }

        Post post = this.mapToEntity(postDTO);
        this.postRepository.save(post);
        return this.mapToDTO(post);

    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts = this.postRepository.findAll();
        return posts.stream()
                .map(entity -> mapToDTO(entity))
                .toList();
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long id) {

        Post post = this.postRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Post not exist"));

        // Check if title already exists in another post 
        postRepository.findByTitle(postDTO.getTitle())
                      .ifPresent(existPost -> {
                         if( !(existPost instanceof Post && 
                               existPost.getId().equals(id))) {
                            throw new EntityFoundException("Exist the title in another post");
                        }
                      });
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setDescription(postDTO.getDescription());

        this.postRepository.save(post);
        return this.mapToDTO(post);

    }
                       


    @Override
    public PostDTO findById(Long id) {
      return this.postRepository.findById(id)
       .map(this::mapToDTO)
       .orElseThrow(() -> new EntityNotFoundException("Post not exist"));
    }

    @Override
    public void deleteById(Long id) {  
      Post post = this.postRepository.findById(id)
      .orElseThrow(() -> new  EntityNotFoundException("Post Not Found"));
      
      this.postRepository.delete(post);
    }



    public Post mapToEntity(PostDTO postDTO){
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        return post;
    }

    public PostDTO mapToDTO(Post post){
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setDescription(post.getDescription());
        return dto;
    }

}
