package com.springboot.swagger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping(path = "/hello")
	public String hello() {
		return "Hello";
	}
	
	@GetMapping(path = "/hello-name")
	public String helloWithParam(@RequestParam String name) {
		return "Hello "+name;
	}
	
}
