package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//controller
@RestController

public class HelloWorldController {
	
	//GET - 
	//URI - /hello-world
	//Method - "Hello world"
	/*
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping(path = "/hello-world-bean")
	//hello-world-bean
	public helloWorldBean helloWorldBean() {
		return new helloWorldBean("Hello world bean");
	}
	*/
	// hello-world/path-variable/in28minutes
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public helloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new helloWorldBean(String.format("Hello world, %s", name));
	}
	
}
