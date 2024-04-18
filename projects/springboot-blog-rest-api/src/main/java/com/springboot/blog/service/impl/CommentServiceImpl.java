package com.springboot.blog.service.impl;

import java.util.List;

import org.hibernate.internal.build.AllowNonPortable;
import org.springframework.stereotype.Service;

import com.springboot.blog.payload.CommentDTO;
import com.springboot.blog.service.CommentService;
import com.springboot.blog.service.PostService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{


	@Override
	public CommentDTO createComment(CommentDTO commentDTO) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'createComment'");
	}

	@Override
	public List<CommentDTO> getCommentByPostId(Long postId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getCommentByPostId'");
	}

	@Override
	public CommentDTO updateComment(CommentDTO commentDTO, Long idComment) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'updateComment'");
	}

	@Override
	public CommentDTO getById(Long idComment) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getById'");
	}

	@Override
	public void delete(Long idPost, Long idComment) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'delete'");
	}

}
