package com.homeProj.service;

import com.homeProj.domain.Vote;

public interface VoteService {

	Vote save(Vote vote);

	void delete(Long id);

}