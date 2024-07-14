package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Saral Khare
 */
@SpringBootApplication
public class MyRestAppApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MyRestAppApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MyRestAppApplication.class, args);
	}

}
