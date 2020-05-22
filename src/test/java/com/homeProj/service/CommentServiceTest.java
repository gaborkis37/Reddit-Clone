package com.homeProj.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.homeProj.domain.Comment;
import com.homeProj.domain.Link;
import com.homeProj.repository.CommentRepository;
import com.homeProj.serviceImpl.CommentServiceImpl;

@ExtendWith(SpringExtension.class)
public class CommentServiceTest {

	@InjectMocks
	private CommentServiceImpl commentService;
	
	@Mock
	private CommentRepository commentRepo;
	
	Link link = new Link();
	Comment comment = new Comment("testBody",link);
	Comment comment2 = new Comment("testBody2",link);
	
	@Test
	public void testSave() {
		when(commentRepo.save(comment)).thenReturn(comment);
		
		Comment actual = commentService.save(comment);
		
		assertEquals("testBody", actual.getBody());
		assertEquals(link, actual.getLink());
	}
	
	@Test
	public void testFindByCreator() {
		when(commentRepo.findByCreatedBy("testCreatedBy")).thenReturn(Arrays.asList(comment,comment2));
		
		List<Comment> comments = commentService.findCommentsByCreator("testCreatedBy");
		
		assertEquals(2, comments.size());
		assertEquals(link,comments.get(0).getLink());
		assertEquals("testBody", comments.get(0).getBody());
		assertEquals(link,comments.get(1).getLink());
		assertEquals("testBody2", comments.get(1).getBody());
	}
	
}
