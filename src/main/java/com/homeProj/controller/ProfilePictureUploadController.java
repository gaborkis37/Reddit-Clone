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

import com.homeProj.domain.User;
import com.homeProj.service.UsersService;

@Controller
public class ProfilePictureUploadController {

	private final UsersService userService;

	public ProfilePictureUploadController(UsersService userService) {
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
	public String upload(@RequestParam("file") MultipartFile file, Principal principal,HttpServletResponse response, Model model) throws IOException {
		User user = userService.findByEmail(principal.getName()).get();
		user.setConfirmPassword(user.getPassword());
		model.addAttribute("user", user);
		userService.storeProfilePicture(user, file);
		return "auth/uploadsuccess";
	}
}
