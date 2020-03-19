package com.homeProj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeProj.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
