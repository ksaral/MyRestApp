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
@RequestMapping(value = "/about")
public class AboutController {

    private static final String WELCOME_MESSAGE = "Welcome to the about page!!";

    @GetMapping(produces = "application/json")
    public @ResponseBody ResponseEntity<WelcomeBean> showAboutPage() {
        Link link = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(HomeController.class)
                        .showHomePage())
                .withRel("home");

        WelcomeBean response = new WelcomeBean();
        response.setLink(link);
        response.setMessage(WELCOME_MESSAGE);

        return new ResponseEntity<WelcomeBean>(response, HttpStatus.OK);
    }

}
