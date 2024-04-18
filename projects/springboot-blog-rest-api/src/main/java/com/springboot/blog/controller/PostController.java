package com.springboot.blog.controller;


import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.payload.PostResponseDTO;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PutMapping;


/**
 * RestController c
 */
@RequestMapping("/api/v1/posts")
@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<PostResponseDTO> getAllPosts(
      @RequestParam(value ="pageNo", defaultValue =  AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
      @RequestParam(value ="pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
      @RequestParam(value ="sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
      @RequestParam(value ="sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false)String sortDir

    ){
        PostResponseDTO postResponseDTO= this.postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
        return ResponseEntity.status(HttpStatus.OK).body(postResponseDTO);
    }

  
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("id") Long id){
        PostDTO dto = this.postService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO){
        PostDTO postCreated = this.postService.createPost(postDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(postCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable("id") Long id, @Valid @RequestBody PostDTO post){
        PostDTO postUpdated = this.postService.updatePost(post, id);
        return ResponseEntity.status(HttpStatus.OK).body(postUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id){
        this.postService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delete");
    }


    

    
}
