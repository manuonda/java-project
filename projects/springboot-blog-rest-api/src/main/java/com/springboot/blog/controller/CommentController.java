package com.springboot.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.CommentDTO;
import com.springboot.blog.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/")

@Tag(name ="CRUD Rest Api Comments Resource",
description = "Rest Api Post Resources Operations")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(
        summary = "Get Comments by Post Id",
        description = "Return all comments by Id Post"
    )
    @ApiResponse( responseCode = "200",
    description = "Http Status 200 Ok")
    @GetMapping("/posts/{id}/comments")
    public List<CommentDTO> getComments(@PathVariable("id") Long idPost) {
        return this.commentService.getCommentByPostId(idPost);
    }


    @Operation(
        summary = "Get Comment by id Comment",
        description = "Get Comment by idComment and Post"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Return status 200 Ok"
    )
    @GetMapping("/posts/{idPost}/comments/{idComment}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable("idPost") Long idPost,
            @PathVariable("idComment") Long idComment) {

        CommentDTO dto = this.commentService.getById(idPost, idComment);
        return ResponseEntity.status(HttpStatus.OK)
                .body(dto);
    }

    @Operation(
        summary = "Create Comment",
        description = "Create Comment in the Post"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Return status 201 CREATED"

    )
    @PostMapping("/posts/{idPost}/comments")
    public ResponseEntity<CommentDTO> postMethodName(
            @PathVariable("idPost") Long idPost,
            @RequestBody CommentDTO comment) {
        CommentDTO commentDTO = this.commentService.createComment(idPost, comment);
        return new ResponseEntity<>(commentDTO, HttpStatus.CREATED);
    }


    @Operation(
        summary = "Update Comment",
        description = "Update Comment with idComment in the post"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Return status 200 OK"
    )

    @PutMapping("/posts/{idPost}/comments/{idComment}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable("idPost") Long idPost,
            @PathVariable("idComment") Long idComment,
            @RequestBody CommentDTO commentDTO) {
        CommentDTO updateDTO = this.commentService.updateComment(idPost, idComment, commentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateDTO);
    }


    @Operation(
        summary = "Delete Comment",
        description = "Delete Comment by IdComment in the post"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Response Status 200 OK"
    )
    @DeleteMapping("/posts/{idPost}/comments/{idComment}")
    public ResponseEntity<String> deleteById(@PathVariable("idPost") Long idPost,
            @PathVariable("idComment") Long idComment) {
        this.commentService.delete(idPost, idComment);
        return ResponseEntity.status(HttpStatus.OK).body("Delete Comment");
    }

}
