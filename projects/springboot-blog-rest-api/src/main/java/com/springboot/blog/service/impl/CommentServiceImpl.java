package com.springboot.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.GlobalExceptionHandler;
import com.springboot.blog.exception.ResourceNotFound;
import com.springboot.blog.payload.CommentDTO;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.modelmapper.ModelMapper;



@Service
public class CommentServiceImpl implements CommentService {

	private final PostRepository postRepository;

	private final CommentRepository commentRepository;

	private final ModelMapper modelMapper;

	public CommentServiceImpl(PostRepository postRepository,
			CommentRepository commentRepository, 
			ModelMapper modelMapper) {
		this.postRepository = postRepository;
		this.commentRepository = commentRepository;
		this.modelMapper = modelMapper;

	}

	@Override
	public CommentDTO createComment(Long idPost, CommentDTO commentDTO) {
		Comment comment = this.modelMapper.map(commentDTO, Comment.class);

		Post post = this.postRepository.findById(idPost)
				.orElseThrow(() -> new ResourceNotFound("Post Not Found"));

		comment.setPost(post);
		Comment commentSaved = this.commentRepository.save(comment);
		return this.modelMapper.map(commentSaved, CommentDTO.class);

	}

	@Override
	public List<CommentDTO> getCommentByPostId(Long postId) {
		List<Comment> comments = this.commentRepository.findByPostId(postId);
		return comments.stream().map(comment -> this.modelMapper.map(comment, CommentDTO.class))
				.toList();
	}

	@Override
	public CommentDTO updateComment(Long idPost, Long idComment, CommentDTO commentDTO) {
		Post post = this.postRepository.findById(idPost)
				.orElseThrow(() -> new ResourceNotFound("Post Not Found : " + (commentDTO.getIdPost())));

		Comment comment = this.commentRepository.findById(idComment)
				.orElseThrow(() -> new ResourceNotFound("Comment Not Found"));

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new GlobalExceptionHandler("Comment does not belong Post");
		}

		comment.setBody(commentDTO.getBody());
		comment.setEmail(commentDTO.getEmail());
		comment.setName(commentDTO.getEmail());

		Comment commentSaved = this.commentRepository.save(comment);
		return this.modelMapper.map(commentSaved, CommentDTO.class);
	}

	@Override
	public CommentDTO getById(Long idPost, Long idComment) {
		Comment comment = this.commentRepository.findById(idComment)
				.orElseThrow(() -> new ResourceNotFound("Comment Not Found"));

		return this.modelMapper.map(comment, CommentDTO.class);
	}

	@Override
	public void delete(Long idPost, Long idComment) {
		Post post = this.postRepository.findById(idPost)
				.orElseThrow(() -> new ResourceNotFound("Post Not Found : " + (idPost)));

		Comment comment = this.commentRepository.findById(idComment)
				.orElseThrow(() -> new ResourceNotFound("Comment Not Found"));

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new GlobalExceptionHandler("Comment does not belong Post");
		}

		this.postRepository.delete(post);
	}

}
