package com.homeProj.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.homeProj.domain.Link;
import com.homeProj.domain.Vote;
import com.homeProj.repository.LinkRepository;
import com.homeProj.repository.VoteRepository;

@RestController
public class VoteController {

	private VoteRepository voteRepo;
	private LinkRepository LinkRepo;

	public VoteController(VoteRepository voteRepo, LinkRepository linkRepo) {
		super();
		this.voteRepo = voteRepo;
		LinkRepo = linkRepo;
	}

	@GetMapping("/vote/link/{linkId}/direction/{direction}/votecount/{voteCount}")
	public int vote(@PathVariable Long linkId, @PathVariable short direction, @PathVariable int voteCount) {
		Optional<Link> optionalLink = LinkRepo.findById(linkId);
		if (optionalLink.isPresent()) {
			Link link = optionalLink.get();
			Vote vote = new Vote(direction, link);
			voteRepo.save(vote);
			int updatedVoteCount = voteCount + direction;
			link.setVoteCount(updatedVoteCount);
			LinkRepo.save(link);
			return updatedVoteCount;
		}
		return voteCount;
	}
}
