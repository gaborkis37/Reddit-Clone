package com.homeProj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeProj.domain.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

}
