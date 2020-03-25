package com.homeProj.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.homeProj.domain.Link;
import com.homeProj.repository.LinkRepository;




@Controller
public class LinkController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LinkController.class);
	private LinkRepository linkRepo;

	public LinkController(LinkRepository linkRepo) {
		this.linkRepo = linkRepo;
	}

	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("links", linkRepo.findAll());
		return "link/list";
	}

	@GetMapping("link/{id}")
	public String read(@PathVariable Long id, Model model) {
		Optional<Link> link = linkRepo.findById(id);
		if (link.isPresent()) {
			model.addAttribute("link", link.get());
			return "link/view";
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/link/submit")
	public String newLinkForm(Model model) {
		model.addAttribute("link", new Link());
		model.addAttribute("success", model.containsAttribute("success"));
		return "link/submit";
	}

	@PostMapping("/link/submit")
	public String createLink(@Valid Link link, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			LOGGER.info("Validation errors were found while submitting a new link");
			model.addAttribute("link", link);
			return "link/submit";
		} else {
			linkRepo.save(link);
			LOGGER.info("New link was saved successfuly");
			redirectAttributes
				.addAttribute("id", link.getId())
				.addFlashAttribute("success", true);
			return "redirect:/link/{id}";
		}
	}

}
