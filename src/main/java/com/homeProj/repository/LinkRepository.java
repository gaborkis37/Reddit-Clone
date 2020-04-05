package com.homeProj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeProj.domain.Link;

public interface LinkRepository extends JpaRepository<Link,Long> {
	
	List<Link> findByUserId(Long id);

}
