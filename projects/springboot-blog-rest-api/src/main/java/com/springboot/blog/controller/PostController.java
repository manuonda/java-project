package com.springboot.blog.controller;


import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.payload.PostResponseDTO;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;

import io.micrometer.core.ipc.http.HttpSender.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RestController
@RequestMapping("api/v1/posts")
@Tag(
    name = "CRUD REST Apis for Post Resource",
    description = "Rest Api for Post Resource Operations"
)
public class PostController {

    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @Operation(
        summary = "Get All Post",
        description = "Get All Post Rest Api is used to get All post paramethers"
    )
    @ApiResponse(
          responseCode = "200",
          description = "Http Status 200 OK"
    )
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


    @Operation(
        summary = "Get Post By Id",
        description = "Get Post By Id, Return un post if exist other wise return error"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Status Code 200 OK" 
  )
  
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("id") Long id){
        PostDTO dto = this.postService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @Operation(
       summary = "Create Post Rest API",
       description ="Create Post Rest API is used to save post in database"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Http Status 201 Created"
    )
    @SecurityRequirement(
        name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO){
        PostDTO postCreated = this.postService.createPost(postDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(postCreated);
    }

    @Operation(
        summary = "Update Post Rest API",
        description = "Update Post Rest Api is Used to update Post in database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Http Status 200 Success"
    )
    @SecurityRequirement(
        name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable("id") Long id, @Valid @RequestBody PostDTO post){
        PostDTO postUpdated = this.postService.updatePost(post, id);
        return ResponseEntity.status(HttpStatus.OK).body(postUpdated);
    }



    @Operation(
         summary = "Delete Post By Id",
         description = "Delete Post By Id is used to delete from database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Http Status 200 Success"
    )

    @SecurityRequirement(
        name = "Bear Authentication"
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id){
        this.postService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delete");
    }

 
    @Operation(
        summary = "Get By Categoria Id",
        description = "Get By Categoria Id is usted to return all post that contain the categoria id "

    )
    @ApiResponse(
        responseCode = "200",
        description = ""
    )
    @RequestMapping("/category/{id}")
    public ResponseEntity<List<PostDTO>> getPostByIdCategoria(@PathVariable("id") Long id){
        List<PostDTO> list = this.postService.getByIdCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    
}
