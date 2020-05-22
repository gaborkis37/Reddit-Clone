package com.homeProj.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.homeProj.domain.Link;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class LinkRepositoryTest {

	@Autowired
	private LinkRepository linkRepo;
	
	@Test
	public void testFindAll() {
		List<Link> links = linkRepo.findAll();
		assertEquals(5, links.size());
	}
	
	@Test
	public void testFindByUserIdBasic() {
		List<Link> links = linkRepo.findByUserId((long) 2);
		assertEquals(3, links.size());
	}
	
	@Test
	public void testFindByUserIdWhenNoUser() {
		List<Link> links = linkRepo.findByUserId((long) 3);
		assertEquals(0, links.size());
	}
	
	@Test
	public void testFindById() {
		Optional<Link> link = linkRepo.findById((long) 1000);
		assertEquals(1000, link.get().getId());
		assertEquals("test@test.com", link.get().getCreatedBy());
		assertEquals("test@test.com", link.get().getLastModifiedBy());
		assertEquals("testTitle", link.get().getTitle());
		assertEquals("testUrl1", link.get().getUrl());
		assertEquals(1,link.get().getUser().getId());
	}
	
	@Test
	public void testSaveLink() {
		Link expected = new Link();
		expected.setCreatedBy("testsave@gmail.com");
		expected.setLastModifiedBy("testsave@gmail.com");
		expected.setTitle("testsavetitle");
		expected.setUrl("testsaveurl");
		
		Link actual = linkRepo.save(expected);
		
		assertEquals(expected.getCreatedBy(),actual.getCreatedBy());
		assertEquals(expected.getLastModifiedBy(), actual.getLastModifiedBy());
		assertEquals(expected.getTitle(), actual.getTitle());
		assertEquals(expected.getUrl(), actual.getUrl());
	}
	
}
