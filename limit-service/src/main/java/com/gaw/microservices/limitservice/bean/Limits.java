package com.gaw.microservices.limitservice.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Limits implements Serializable{
    private int minimum;
    private int maximum;

}