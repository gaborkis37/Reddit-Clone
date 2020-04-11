package com.homeProj.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.homeProj.domain.Comment;
import com.homeProj.domain.Link;
import com.homeProj.domain.User;
import com.homeProj.service.LinkService;
import com.homeProj.service.UsersService;
import com.homeProj.serviceImpl.CommentServiceImpl;

@Controller
public class LinkController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LinkController.class);
	private final LinkService linkService;
	private final CommentServiceImpl commentService;
	private final UsersService userService;

	public LinkController(LinkService linkService, CommentServiceImpl commentService, UsersService userService) {
		super();
		this.linkService = linkService;
		this.commentService = commentService;
		this.userService = userService;
	}

	@GetMapping("/")
	public String list(Model model) {
		List<Link> links = linkService.findAll();
		model.addAttribute("links", links);
		return "link/list";
	}

	@GetMapping("link/{id}")
	public String read(@PathVariable Long id, Model model) {
		Optional<Link> link = linkService.findById(id);
		if (link.isPresent()) {
			Link currentLink = link.get();
			Comment comment = new Comment();
			comment.setLink(currentLink);
			model.addAttribute("comment", comment);
			model.addAttribute("link", currentLink);
			model.addAttribute("success", model.containsAttribute("success"));
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
			RedirectAttributes redirectAttributes, Principal principal) {
		if (bindingResult.hasErrors()) {
			LOGGER.info("Validation errors were found while submitting a new link");
			model.addAttribute("link", link);
			return "link/submit";
		} else {
			String email = principal.getName();
			Optional<User> user = userService.findByEmail(email);
			if (user.isPresent()) {
				User creator = user.get();
				link.setUser(creator);
			}
			linkService.save(link);
			LOGGER.info("New link was saved successfuly");
			redirectAttributes.addAttribute("id", link.getId()).addFlashAttribute("success", true);
			return "redirect:/link/{id}";
		}
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@PostMapping("/link/comments")
	public String comments(@Valid Comment comment, BindingResult bindigResult) {
		if (bindigResult.hasErrors()) {
			LOGGER.info("there was a problem while adding a new comment");
		} else {
			commentService.save(comment);
			LOGGER.info("comment was saved successfully");
		}

		return "redirect:/link/" + comment.getLink().getId();
	}

}
