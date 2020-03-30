package com.homeProj.service;

import org.springframework.stereotype.Service;

import com.homeProj.domain.Vote;
import com.homeProj.repository.VoteRepository;

@Service
public class VoteService {

	private final VoteRepository voteRepository;

	public VoteService(VoteRepository voteRepository) {
		super();
		this.voteRepository = voteRepository;
	}

	public Vote save(Vote vote) {
		return voteRepository.save(vote);
	}
}
