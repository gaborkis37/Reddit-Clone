package com.homeProj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeProj.domain.Link;

public interface LinkRepository extends JpaRepository<Link,Long> {

}
