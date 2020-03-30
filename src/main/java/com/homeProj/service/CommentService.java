package com.homeProj.service;

import org.springframework.stereotype.Service;

import com.homeProj.domain.Comment;
import com.homeProj.repository.CommentRepository;

@Service
public class CommentService {

	private final CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		super();
		this.commentRepository = commentRepository;
	}

	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}
}
