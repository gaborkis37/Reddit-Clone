package com.homeProj.service;

import java.security.Principal;
import java.util.Optional;

import com.homeProj.domain.Link;
import com.homeProj.domain.Vote;

public interface VoteService {

	Vote save(Vote vote);

	void delete(Long id);

	int vote(Long linkId, short direction, Principal principal, int voteCount, Link link);

	Optional<Vote> findOneVoteByCreatorDirectionAndLink(String createdBy, short direction, Long linkId);

	Optional<Vote> findUpVote(String createdBy, Long linkId);

	Optional<Vote> findDownVote(String createdBy, Long linkId);

}