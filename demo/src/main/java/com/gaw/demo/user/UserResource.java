package com.gaw.demo.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.gaw.demo.exception.NotFoundException;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		List<User> result = service.findAll();
		return result;
	}
	/* for filtering demo
	public MappingJacksonValue retrieveAllUsers(){
		List<User> result = service.findAll();
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(result);
		mapping.setFilters(filters);
		
		return mapping;
	} */
	
	@GetMapping("/users/{id}")
	public MappingJacksonValue retrieveUser(@PathVariable int id){
		User user = service.findOne(id);
		if (user == null) {
			throw new NotFoundException("id-" + id + " gak ketemu");
		}
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name", "birthDate");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(user);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		
		if (user == null)
			throw new NotFoundException("Mau delete id-" + id + " tapi gak ketemu");
	}
}
