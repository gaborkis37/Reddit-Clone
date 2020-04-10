package com.homeProj.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.homeProj.domain.Link;
import com.homeProj.service.LinkService;
import com.homeProj.serviceImpl.VoteServiceImpl;

@RestController
public class VoteController {

	private VoteServiceImpl voteService;
	private LinkService linkService;

	public VoteController(VoteServiceImpl voteService, LinkService linkService) {
		super();
		this.voteService = voteService;
		this.linkService = linkService;
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping("/vote/link/{linkId}/direction/{direction}/votecount/{currentVoteCount}")
	public int vote(@PathVariable Long linkId, @PathVariable short direction, @PathVariable int currentVoteCount,
			Principal principal) {
		Optional<Link> optLink = linkService.findById(linkId);
		int voteCount = currentVoteCount;
		if (optLink.isPresent()) {
			Link link = optLink.get();
			voteCount = voteService.vote(linkId, direction, principal, voteCount, link);
			link.setVoteCount(voteCount);
			linkService.save(link);
		}
		return voteCount;
	}

}
