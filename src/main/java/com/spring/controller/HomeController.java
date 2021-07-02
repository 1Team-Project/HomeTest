package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j2;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log4j2
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {

		log.info("welcome home!");
		
		return "index";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "AccessDenied";
	}

	
}
