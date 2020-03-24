package com.homeProj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.homeProj.repository.LinkRepository;

@Controller
public class LinkController {

	private LinkRepository linkRepo;

	public LinkController(LinkRepository linkRepo) {
		this.linkRepo = linkRepo;
	}

	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("links", linkRepo.findAll());
		return "link/list";
	}

}
