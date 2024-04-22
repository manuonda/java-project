package com.springboot.blog.service;


import java.util.List;

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
   CommentDTO createComment(Long idPost, CommentDTO commentDTO);

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
    * @param idPost
    * @return
    */
   CommentDTO updateComment(Long idPost, Long idComment, CommentDTO commentDTO );

   /**
    * GetByIdComment
    * @param idComment
    * @return
    */
   CommentDTO getById(Long idPost, Long idComment);

   /**
    * Delete Comment
    * @param idPost
    * @param idComment
    */
   void delete(Long idPost, Long idComment);


}
