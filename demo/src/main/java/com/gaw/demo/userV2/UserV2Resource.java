package com.gaw.demo.userV2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserV2Resource {
    @Autowired
	private UserV2DaoService service;

    @GetMapping("v2/users")
	public List<UserV2> retrieveAllUsersV2Uri(){
		List<UserV2> result = service.findAll();
		return result;
	}

    @GetMapping(value="/users/param", params = "version=2")
	public List<UserV2> retrieveAllUsersV2Params(){
		List<UserV2> result = service.findAll();
		return result;
	}

    @GetMapping(value="/users/header", headers = "X-API-VERSION=2")
	public List<UserV2> retrieveAllUsersV2Headers(){
		List<UserV2> result = service.findAll();
		return result;
	}

    @GetMapping(value="/users/produces", produces = "application/vnd.company.app-v1+json")
	public String retrieveAllUsersV2Produces1(){
		return "NOPE";
	}

    @GetMapping(value="/users/produces", produces = "application/vnd.company.app-v2+json")
	public List<UserV2> retrieveAllUsersV2Produces(){
		List<UserV2> result = service.findAll();
		return result;
	}
}
