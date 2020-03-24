package com.homeProj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LinkController {

	@GetMapping("/foo")
	public String foo(Model model) {
		model.addAttribute("pageTitle", "fooTitle");
		return "foo";
	}

}
