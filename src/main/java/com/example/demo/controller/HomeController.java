package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.datamodel.WelcomeBean;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value = "/home")
public class HomeController {

    private static final String WELCOME_MESSAGE = "Welcome to the home page!!";

    @GetMapping(produces = "application/json")
    public @ResponseBody ResponseEntity<WelcomeBean> getMethodName() {
        Link link = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(AboutController.class)
                        .getMethodName())
                .withRel("about");

        WelcomeBean response = new WelcomeBean();
        response.setLink(link);
        response.setMessage(WELCOME_MESSAGE);

        return new ResponseEntity<WelcomeBean>(response, HttpStatus.OK);
    }
    

}
