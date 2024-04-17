package com.springboot.blog.service;


import java.util.List;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.payload.CommentDTO;

/***
 * Class Service Comment
 */
public interface CommentService {


   /**
    * Create Comment DTO
    * @param commentDTO
    * @return
    */ 
   CommentDTO createComment(CommentDTO commentDTO);

   /**
    * 
    * @param postId
    * @return
    */
   List<CommentDTO> getCommentByPostId(Long postId);

   /**
    * Update Commetn
    * @param commentDTO
    * @param idComment
    * @return
    */
   CommentDTO updateComment(CommentDTO commentDTO , Long idComment);

   /**
    * GetByIdComment
    * @param idComment
    * @return
    */
   CommentDTO getById(Long idComment);

   /**
    * Delete Comment
    * @param idPost
    * @param idComment
    */
   void delete(Long idPost, Long idComment);


}
