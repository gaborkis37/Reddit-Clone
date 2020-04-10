package com.homeProj.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.homeProj.domain.Link;
import com.homeProj.repository.LinkRepository;
import com.homeProj.service.LinkService;

@Service
public class LinkServiceImpl implements LinkService {

	private final LinkRepository linkRepository;

	public LinkServiceImpl(LinkRepository linkRepository) {
		super();
		this.linkRepository = linkRepository;
	}

	@Override
	public List<Link> findAll() {
		return linkRepository.findAll();
	}

	@Override
	public Optional<Link> findById(Long id) {
		return linkRepository.findById(id);
	}

	@Override
	public Link save(Link link) {
		return linkRepository.save(link);
	}
	
	@Override
	public List<Link> findByUserId(Long id) {
		return linkRepository.findByUserId(id);
	}
}
