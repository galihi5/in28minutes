package com.gaw.demo.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//controller
@RestController
public class HelloController {
	// GET
	// URI - /hello-world
	// method - "Hello World"

	@RequestMapping(method = RequestMethod.GET, path = "/hello-world-1")
	public String helloWorld1() {
		return "Hello World 1";
	}

	@GetMapping("/hello-world-2")
	public String helloWorld2() {
		return "Hello World 2";
	}

	// hello-world-bean
	@GetMapping("/hello-world-bean")
	public Hello helloWorldBean() {
		return new Hello(234, "This is hello world bean");
	}

	// hello-world-bean
	@GetMapping("/hello-world/{message}")
	public Hello helloWorldPathVariable(@PathVariable String message) {
		return new Hello(123, String.format("Hello world message : %s", message));
	}
}
