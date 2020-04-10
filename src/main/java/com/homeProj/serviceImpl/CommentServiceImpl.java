package com.homeProj.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.homeProj.domain.Comment;
import com.homeProj.repository.CommentRepository;
import com.homeProj.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;

	public CommentServiceImpl(CommentRepository commentRepository) {
		super();
		this.commentRepository = commentRepository;
	}

	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}
	
	public List<Comment> findCommentsByCreator(String creator) {
		return commentRepository.findByCreatedBy(creator);
	}
}
