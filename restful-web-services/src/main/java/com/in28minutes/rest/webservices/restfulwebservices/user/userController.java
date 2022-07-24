package com.in28minutes.rest.webservices.restfulwebservices.user;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.*;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class userController {
	
	@Autowired
	private UserDaoService service;
	
	//GET /users
	//retrieve all user
	@GetMapping("/users")
	public List<User> retrieveAllUser(){
		return service.findAll();
		
	}
	
	//GET /user/{id}
	// retrieve user(user id)
	@GetMapping("/users/{id}")
	public User retrieveOneUser(@PathVariable int id) {
		
		User user = service.findOne(id);
		if (user == null) 
			throw new UserNotFoundException("id-" + id);
		//"all-users" , SERVER_PATH + "/users"
		//retrieve all user
		return user;
	}
	
	// create user
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		//CREATED
  		// /users/{id}   savedUser.getId()
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deletebById(id);
		if (user == null)
			throw new UserNotFoundException("id-" + id);
	}
	
		
}
