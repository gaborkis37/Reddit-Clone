package com.homeProj.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.homeProj.domain.Comment;
import com.homeProj.domain.Link;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CommentRepositoryTest {

	@Autowired
	private CommentRepository commentRepo;
	
	@Test
	public void testFindAll() {
		List<Comment> comments = commentRepo.findAll();
		assertEquals(4, comments.size());
	}
	
	@Test 
	public void testSave() {
		Link link = new Link();
		Comment expected = new Comment("testBody", link);
		
		Comment actual = commentRepo.save(expected);
		
		assertEquals(2, actual.getId());
		assertEquals(expected.getBody(), actual.getBody());
		assertEquals(expected.getLink(), actual.getLink());
		
	}
	
	@Test
	public void testFindByCreator() {
		String creator = "testCreator";
		
		List<Comment> comments = commentRepo.findByCreatedBy(creator);
		
		assertEquals(3, comments.size());
		assertEquals(creator, comments.get(0).getCreatedBy());
		assertEquals(creator, comments.get(1).getCreatedBy());
		assertEquals(creator, comments.get(2).getCreatedBy());
	}
	
}
