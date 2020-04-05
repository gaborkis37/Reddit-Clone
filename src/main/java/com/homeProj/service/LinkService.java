package com.homeProj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.homeProj.domain.Link;
import com.homeProj.repository.LinkRepository;

@Service
public class LinkService {

	private final LinkRepository linkRepository;

	public LinkService(LinkRepository linkRepository) {
		super();
		this.linkRepository = linkRepository;
	}

	public List<Link> findAll() {
		return linkRepository.findAll();
	}

	public Optional<Link> findById(Long id) {
		return linkRepository.findById(id);
	}

	public Link save(Link link) {
		return linkRepository.save(link);
	}
	
	public List<Link> findByUserId(Long id) {
		return linkRepository.findByUserId(id);
	}
}
