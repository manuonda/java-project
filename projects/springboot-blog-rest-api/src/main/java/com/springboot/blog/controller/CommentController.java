package com.springboot.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.CommentDTO;
import com.springboot.blog.service.CommentService;

import io.micrometer.core.ipc.http.HttpSender.Response;

import java.util.List;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/posts/{id}/comments")
    public List<CommentDTO> getComments(@PathVariable("id") Long idPost) {
        return this.commentService.getCommentByPostId(idPost);
    }

    @GetMapping("/posts/{idPost}/comments/{idComment}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable("idPost") Long idPost,
            @PathVariable("idComment") Long idComment) {

        CommentDTO dto = this.commentService.getById(idPost, idComment);
        return ResponseEntity.status(HttpStatus.OK)
                .body(dto);
    }

    @PostMapping("/posts/{idPost}/comments")
    public ResponseEntity<CommentDTO> postMethodName(
            @PathVariable("idPost") Long idPost,
            @RequestBody CommentDTO comment) {
        CommentDTO commentDTO = this.commentService.createComment(idPost, comment);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @PutMapping("/posts/{idPost}/comments/{idComment}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable("idPost") Long idPost,
            @PathVariable("idComment") Long idComment,
            @RequestBody CommentDTO commentDTO) {
        CommentDTO updateDTO = this.commentService.updateComment(idPost, idComment, commentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateDTO);
    }

    @DeleteMapping("/posts/{idPost}/comments/{idComment}")
    public ResponseEntity<String> deleteById(@PathVariable("idPost") Long idPost,
            @PathVariable("idComment") Long idComment) {
        this.commentService.delete(idPost, idComment);
        return ResponseEntity.status(HttpStatus.OK).body("Delete Comment");
    }

}
