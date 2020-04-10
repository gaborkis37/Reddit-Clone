package com.homeProj.service;

import java.util.List;
import java.util.Optional;

import com.homeProj.domain.Link;

public interface LinkService {

	List<Link> findAll();

	Optional<Link> findById(Long id);

	Link save(Link link);

	List<Link> findByUserId(Long id);

}