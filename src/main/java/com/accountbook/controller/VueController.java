package com.accountbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VueController {
	
	@GetMapping("/")
	public String showVuePage() {
		return "/index.html";
	}
	
	@GetMapping("/login")
	public String showVueLoginPage() {
		return "/index.html";
	}
}
