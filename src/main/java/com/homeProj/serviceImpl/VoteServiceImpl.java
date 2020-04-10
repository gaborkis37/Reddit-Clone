package com.homeProj.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.homeProj.domain.Vote;
import com.homeProj.repository.VoteRepository;
import com.homeProj.service.VoteService;

@Service
public class VoteServiceImpl implements VoteService {

	private final VoteRepository voteRepository;

	public VoteServiceImpl(VoteRepository voteRepository) {
		super();
		this.voteRepository = voteRepository;
	}

	@Override
	public Vote save(Vote vote) {
		return voteRepository.save(vote);
	}

	public List<Vote> findByCreatedByAndDirectionAndLinkId(String createdBy, short direction, Long linkId) {
		System.out.println("PARAMS: " + createdBy + " " + direction + " " + linkId);
		return voteRepository.findByCreatedByAndDirectionAndLinkId(createdBy, direction, linkId);
	}

	@Override
	public void delete(Long id) {
		voteRepository.deleteById(id);
	}

}
