package com.gaw.microservices.limitservice.controller;

import com.gaw.microservices.limitservice.bean.Limits;
import com.gaw.microservices.limitservice.configuration.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController{
    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public Limits retrieveLimits(){
        // return new Limits(1, 1000);
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}