package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AllExceptionController {

	@ExceptionHandler(value = ProductNotFoundException.class)
	public ResponseEntity<Object> exception(ProductNotFoundException exception) {
		return new ResponseEntity<>("This product does not exist.", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = ProductAlreadyExistsException.class)
	public ResponseEntity<Object> exception(ProductAlreadyExistsException exception) {
		return new ResponseEntity<>("This product already exists, you cannot create a new product with " + exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
