package com.example.demo.datamodel;

import org.springframework.hateoas.Link;

import lombok.Data;

@Data
public class WelcomeBean {

    private String message;
    private Link link;

}
