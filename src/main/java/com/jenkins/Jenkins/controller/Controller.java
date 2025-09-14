package com.jenkins.Jenkins.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get")
public class Controller {

	
	
	@GetMapping
	public String getMesaage() {
		return "welcome to jenkins";
	}
}
