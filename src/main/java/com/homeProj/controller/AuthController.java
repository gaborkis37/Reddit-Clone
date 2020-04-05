package com.homeProj.controller;

import java.security.Principal;
import java.util.List;
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

import com.homeProj.domain.Comment;
import com.homeProj.domain.Link;
import com.homeProj.domain.User;
import com.homeProj.service.CommentService;
import com.homeProj.service.LinkService;
import com.homeProj.service.UserService;

@Controller
public class AuthController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
	private final UserService userService;
	private final LinkService linkService;
	private final CommentService commentService;

	public AuthController(UserService userService, LinkService linkService, CommentService commentService) {
		super();
		this.userService = userService;
		this.linkService = linkService;
		this.commentService = commentService;
	}

	@GetMapping("/login")
	public String login() {
		return "auth/login";
	}

	@GetMapping("/profile")
	public String profile(Model model, Principal principal) {
		Optional<User> user = userService.findByEmail(principal.getName());
		if (user.isPresent()) {
			User currentUser = user.get();
			List<Comment> comments = commentService.findCommentsByCreator(currentUser.getEmail());
			List<Link> linksOfUser = linkService.findByUserId(currentUser.getId());
			LOGGER.info("num of links by user: " + linksOfUser.size());
			model.addAttribute("numOfLinks", linksOfUser.size());
			model.addAttribute("numOfComments", comments.size());
			model.addAttribute("userSince", currentUser.getCreated());
			model.addAttribute("linksOfUser", linksOfUser);
		}
		return "auth/profile";
	}
	
	@GetMapping("/u/{alias}")
	public String userProfile(@PathVariable String alias, Model model) {
		Optional<User> user = userService.findByAlias(alias);
		if(user.isPresent()) {
			User currentUser = user.get();
			List<Comment> comments = commentService.findCommentsByCreator(currentUser.getEmail());
			List<Link> linksOfUser = linkService.findByUserId(currentUser.getId());
			LOGGER.info("num of links by user: " + linksOfUser.size());
			model.addAttribute("numOfLinks", linksOfUser.size());
			model.addAttribute("numOfComments", comments.size());
			model.addAttribute("userSince", currentUser.getCreated());
			model.addAttribute("linksOfUser", linksOfUser);
		}
		return "auth/profile";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "auth/register";
	}

	@PostMapping("/register")
	public String registerNewUser(@Valid User user, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			LOGGER.info("validation errors during registration");
			model.addAttribute("user", user);
			model.addAttribute("validationErrors", bindingResult.getAllErrors());
			return "auth/register";
		} else {
			User newUser = userService.register(user);
			redirectAttributes.addAttribute("id", newUser.getId()).addFlashAttribute("success", true);
			return "redirect:/register";
		}
	}

	@GetMapping("/activate/{email}/{activationCode}")
	public String activate(@PathVariable String email, @PathVariable String activationCode) {
		Optional<User> user = userService.findByEmailAndActivationCode(email, activationCode);
		if (user.isPresent()) {
			User newUser = user.get();
			newUser.setEnabled(true);
			newUser.setConfirmPassword(newUser.getPassword());
			userService.save(newUser);
			userService.sendWelcomeEmail(newUser);
			return "auth/activated";
		}
		return "rerdirect:/";
	}

}
