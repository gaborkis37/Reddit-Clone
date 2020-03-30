package com.homeProj.controller;

import java.util.Optional;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.homeProj.domain.Link;
import com.homeProj.domain.Vote;
import com.homeProj.service.LinkService;
import com.homeProj.service.VoteService;

@RestController
public class VoteController {

	private VoteService voteService;
	private LinkService linkService;

	public VoteController(VoteService voteService, LinkService linkService) {
		super();
		this.voteService = voteService;
		this.linkService = linkService;
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping("/vote/link/{linkId}/direction/{direction}/votecount/{voteCount}")
	public int vote(@PathVariable Long linkId, @PathVariable short direction, @PathVariable int voteCount) {
		Optional<Link> optionalLink = linkService.findById(linkId);
		if (optionalLink.isPresent()) {
			Link link = optionalLink.get();
			Vote vote = new Vote(direction, link);
			voteService.save(vote);
			int updatedVoteCount = voteCount + direction;
			link.setVoteCount(updatedVoteCount);
			linkService.save(link);
			return updatedVoteCount;
		}
		return voteCount;
	}
}
