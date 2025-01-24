package com.example.demo.service;

import java.util.Collection;

import com.example.demo.datamodel.Product;

public interface ProductService {

	Collection<Product> getProducts();

	Product getProduct(Integer productId);

	void addProduct(Product product);

	void updateProduct(Integer productId, Product product);

	void deleteProduct(Integer productId);

}
