package com.homeProj.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.homeProj.domain.User;
import com.homeProj.service.UsersService;

@Controller
public class ProfilePictureController {

	private final UsersService userService;

	public ProfilePictureController(UsersService userService) {
		super();
		this.userService = userService;
	}
	
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping("/upload")
	public String uploadPage() {
		return "auth/upload";
	}
	
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file, Principal principal,HttpServletResponse response, Model model, RedirectAttributes attributes) throws IOException {
		User user = userService.findByEmail(principal.getName()).get();
		user.setConfirmPassword(user.getPassword());
		model.addAttribute("user", user);
		userService.storeProfilePicture(user, file);
		attributes.addFlashAttribute("uploadSuccess", true);
		return "redirect:/profile";
	}
	
	@PostMapping("/delete") 
	public String delete(Principal principal, RedirectAttributes attributes) {
		User user = userService.findByEmail(principal.getName()).get();
		user.setConfirmPassword(user.getPassword());
		userService.deleteProfilePicture(user);
		attributes.addFlashAttribute("deleteSuccess", true);
		return "redirect:/profile";
	}
}
