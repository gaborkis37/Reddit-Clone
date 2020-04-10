package com.homeProj.serviceImpl;

import java.security.Principal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.homeProj.domain.Link;
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

	@Override
	public void delete(Long id) {
		voteRepository.deleteById(id);
	}
	
	@Override
	public Optional<Vote> findOneVoteByCreatorDirectionAndLink(String createdBy, short direction, Long linkId) {
		return voteRepository.findOneByCreatedByAndDirectionAndLinkId(createdBy, direction, linkId);
	}
	
	@Override
	public Optional<Vote> findUpVote(String createdBy, Long linkId) {
		return voteRepository.findUpVote(createdBy, linkId);
	}
	
	@Override
	public Optional<Vote> findDownVote(String createdBy, Long linkId) {
		return voteRepository.findDownVote(createdBy, linkId);
	}

	@Override
	public int vote(Long linkId, short direction, Principal principal, int voteCount, Link link) {
		Optional<Vote> optUpVote = findUpVote(principal.getName(), linkId);
		Optional<Vote> optDownVote = findDownVote(principal.getName(), linkId);
		Vote vote = new Vote(direction, link);
		if (isAnUpvote(direction)) {
			voteCount = upvoteMethod(voteCount, optUpVote, optDownVote, vote);
		}
		if (isADownVote(direction)) {
			voteCount = downvoteMethod(voteCount, optUpVote, optDownVote, vote);
		}
		return voteCount;
	}

	private int downvoteMethod(int voteCount, Optional<Vote> optUpVote, Optional<Vote> optDownVote, Vote vote) {
		if (noUpOrDownvotesInDb(optUpVote, optDownVote)) {
			save(vote);
			voteCount = voteCount - 1;
		}
		if (aDownVoteIsInDb(optDownVote)) {
			delete(optDownVote.get().getId());
			voteCount = voteCount + 1;
		}
		if (anUpVoteIsInDb(optUpVote)) {
			delete(optUpVote.get().getId());
			save(vote);
			voteCount = voteCount - 2;
		}
		return voteCount;
	}

	private int upvoteMethod(int voteCount, Optional<Vote> optUpVote, Optional<Vote> optDownVote, Vote vote) {
		if (noUpOrDownvotesInDb(optUpVote, optDownVote)) {
			save(vote);
			voteCount = voteCount + 1;
		}
		if (anUpVoteIsInDb(optUpVote)) {
			delete(optUpVote.get().getId());
			voteCount = voteCount - 1;
		}
		if (aDownVoteIsInDb(optDownVote)) {
			delete(optDownVote.get().getId());
			save(vote);
			voteCount = voteCount + 2;
		}
		return voteCount;
	}

	private boolean aDownVoteIsInDb(Optional<Vote> optDownVote) {
		return optDownVote.isPresent();
	}

	private boolean anUpVoteIsInDb(Optional<Vote> optUpVote) {
		return aDownVoteIsInDb(optUpVote);
	}

	private boolean noUpOrDownvotesInDb(Optional<Vote> optUpVote, Optional<Vote> optDownVote) {
		return optDownVote.isEmpty() && optUpVote.isEmpty();
	}

	private boolean isADownVote(short direction) {
		return direction == -1;
	}

	private boolean isAnUpvote(short direction) {
		return direction == 1;
	}
}
