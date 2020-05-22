package com.homeProj.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.homeProj.domain.Link;
import com.homeProj.domain.User;
import com.homeProj.repository.LinkRepository;
import com.homeProj.serviceImpl.LinkServiceImpl;

@ExtendWith(SpringExtension.class)
public class LinkServiceTest {
	
	Link expected = new Link("testTitle","testUrl");
	Link expected2 = new Link("testTitle2","testUrl2");
	

	@InjectMocks
	private LinkServiceImpl linkService;

	@Mock
	private LinkRepository linkRepo;

	@Test
	public void testFindAll() {
		when(linkRepo.findAll()).thenReturn(Arrays.asList(expected,expected2));

		List<Link> links = linkService.findAll();
		assertEquals(2, links.size());
		assertEquals("testTitle", links.get(0).getTitle());
		assertEquals("testUrl", links.get(0).getUrl());
		assertEquals("testTitle2", links.get(1).getTitle());
		assertEquals("testUrl2", links.get(1).getUrl());
	}
	
	@Test
	public void testFindById() {
		expected.setId((long) 1);
		when(linkRepo.findById((long) 1)).thenReturn(Optional.of(expected));
		
		Link actual = linkService.findById((long) 1).get();
		
		assertEquals(1, actual.getId());
		assertEquals("testTitle", actual.getTitle());
		assertEquals("testUrl", actual.getUrl());
		
	}
	
	@Test
	public void testSave() {
		expected.setId((long) 1);
		
		when(linkRepo.save(expected)).thenReturn(expected);
		
		Link actual = linkService.save(expected);
		
		assertEquals(1,actual.getId());
		assertEquals("testTitle",actual.getTitle());
		assertEquals("testUrl",actual.getUrl());
	}

	@Test
	public void testFindByUserId() {
		User user = new User("testemail", "testpass", true, "testFirstName", "testLastName", "testAlias");
		user.setId((long) 1);
		
		expected.setId((long) 1);
		expected.setUser(user);
		expected2.setId((long) 2);
		expected2.setUser(user);
		
		when(linkRepo.findByUserId((long) 1)).thenReturn(Arrays.asList(expected,expected2));
		
		List<Link> actualLinks = linkService.findByUserId((long) 1);
		
		assertEquals(1, actualLinks.get(0).getId());
		assertEquals("testTitle", actualLinks.get(0).getTitle());
		assertEquals("testUrl", actualLinks.get(0).getUrl());
		assertEquals(1, actualLinks.get(0).getUser().getId());
		assertEquals(2, actualLinks.get(1).getId());
		assertEquals("testTitle2", actualLinks.get(1).getTitle());
		assertEquals("testUrl2", actualLinks.get(1).getUrl());
		assertEquals(1, actualLinks.get(1).getUser().getId());
		
	}
}
