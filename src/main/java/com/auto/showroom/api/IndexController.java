package com.auto.showroom.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

	@GetMapping
	public String get() {
		return "GET Spring Boot 2!";
	}
	
	@PostMapping
	public String post() {
		return "POST Spring Boot 2!";
	}
	
	@PutMapping
	public String put() {
		return "PUT Spring Boot 2!";
	}
	
	@DeleteMapping
	public String delete() {
		return "DELETE Spring Boot 2!";
	}
	
}
