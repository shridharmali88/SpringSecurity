package com.nit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WishMessageGeneratorController {

	public WishMessageGeneratorController() {
		System.out.println("WishMessageGeneratorController:: 0 param Constructor for "+this.getClass());
	}
	
	@GetMapping(value = "/home.htm")
	public String showHomePage() {
		return "home";
	}
	
	@GetMapping(value = "/wish.htm")
	public String showWishMsg() {
		return "wish";
	}
}
