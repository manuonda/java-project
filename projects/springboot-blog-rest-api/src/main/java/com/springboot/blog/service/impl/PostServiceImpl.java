package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.EntityFoundException;
import com.springboot.blog.exception.ResourceNotFound;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.payload.PostResponseDTO;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {


    private final PostRepository postRepository;
    private final ModelMapper model;

    public PostServiceImpl(PostRepository postRepository , 
                           ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.model = modelMapper;
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
    public PostResponseDTO getAllPosts(int pageNo,int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
        : Sort.by(sortBy).descending();

        //create pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
        Page<Post> pagePosts = this.postRepository.findAll(pageable);
        //get content for page object
        List<Post> posts = pagePosts.getContent();

        List<PostDTO> postDTOs =  posts.stream()
                .map(this::mapToDTO)
                .toList();

        return  new PostResponseDTO(
            postDTOs, 
            pagePosts.getNumber(), 
            pagePosts.getSize(), 
            pagePosts.getTotalElements(), 
            pagePosts.getTotalPages(), 
            pagePosts.isLast()); 
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long id) {

        Post post = this.postRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFound("Post not exist"));

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
       .orElseThrow(() -> new ResourceNotFound("Post not exist"));
    }

    @Override
    public void deleteById(Long id) {  
      Post post = this.postRepository.findById(id)
      .orElseThrow(() -> new  ResourceNotFound("Post Not Found"));
      
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

    @Override
    public List<PostDTO> getByIdCategory(Long id) {
        List<Post> list = this.postRepository.findByIdCategory(id);
        return list.stream()
        .map(post ->  this.model.map(post, PostDTO.class))
        .toList();
    }

}
