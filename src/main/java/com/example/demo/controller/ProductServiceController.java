package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.datamodel.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductServiceController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public @ResponseBody ResponseEntity<Object> getAllProducts() {
		return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
	}

	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		productService.addProduct(product);
		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}

	@PutMapping(value = "/{productId}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> updateProduct(@PathVariable Integer productId, @RequestBody Product product) {
		productService.updateProduct(productId, product);
		return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
	}

	@DeleteMapping(value = "/{productId}", produces = "application/json")
	public ResponseEntity<Object> deleteProduct(@PathVariable Integer productId) {
		productService.deleteProduct(productId);
		return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/{productId}", produces = "application/json")
	public ResponseEntity<Object> getProduct(@PathVariable Integer productId) {
		return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
	}

}
