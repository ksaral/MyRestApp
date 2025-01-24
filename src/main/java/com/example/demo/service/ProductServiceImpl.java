package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.datamodel.Product;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private static Map<Integer, Product> productsRepo = new HashMap<>();
	@Autowired
	private ProductRepository productRepository;

	static {
		Product demoProduct = new Product();
		demoProduct.setProductId(1);
		demoProduct.setProductName("Demo product");
		productsRepo.put(demoProduct.getProductId(), demoProduct);
	}

	@Override
	public Collection<Product> getProducts() {
		// return productsRepo.values();
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(productFromDb -> {
			products.add(createModelFromEntity(productFromDb));
		});
		return products;
	}

	@Override
	public Product getProduct(Integer productId) {
		/*if (null == productsRepo.get(productId))
			throw new ProductNotFoundException();
		return productsRepo.get(productId);*/
		// Below is the method for version 2.X.X.
		// com.example.demo.entity.Product productFromDb = productRepository.findById(productId).orElse(null);
		// Below is the method for version 1.X.X.
		com.example.demo.entity.Product productFromDb = productRepository.findOne(productId);
		if (null == productFromDb)
			throw new ProductNotFoundException();
		return createModelFromEntity(productFromDb);
	}

	@Override
	public void addProduct(Product product) {
		//product.setProductId(productsRepo.size() + 1);
		//productsRepo.put(product.getProductId(), product);
		productRepository.save(createEntityFromModel(product));
	}

	@Override
	public void updateProduct(Integer productId, Product product) {
		/*if (null == productsRepo.get(productId))
			throw new ProductNotFoundException();
		if (StringUtils.isEmpty(product.getProductId()))
			product.setProductId(productId);
		productsRepo.put(productId, product);*/

		// Below is the method for version 2.X.X.
		// com.example.demo.entity.Product productFromDb = productRepository.findById(productId).orElse(null);
		// Below is the method for version 1.X.X.
		com.example.demo.entity.Product productFromDB = productRepository.findOne(productId);
		if (null == productFromDB)
			throw new ProductNotFoundException();
		
		if (StringUtils.isEmpty(product.getProductId()))
			product.setProductId(productFromDB.getProductId());
		productRepository.save(updateEntityFromModel(productFromDB, product));
	}

	@Override
	public void deleteProduct(Integer productId) {
		/*if (null == productsRepo.get(productId))
			throw new ProductNotFoundException();

		productsRepo.remove(productId);*/
		// Below is the method for version 2.X.X.
		// com.example.demo.entity.Product productFromDb = productRepository.findById(productId).orElse(null);
		// Below is the method for version 1.X.X.
		com.example.demo.entity.Product productFromDB = productRepository.findOne(productId);
		if (null == productFromDB)
			throw new ProductNotFoundException();
		productRepository.delete(productFromDB);
	}
	
	private Product createModelFromEntity(com.example.demo.entity.Product productFromDb) {
		Product product = new Product();
		product.setProductId(productFromDb.getProductId());
		product.setProductName(productFromDb.getProductName());
		return product;
	}

	private com.example.demo.entity.Product createEntityFromModel(Product productModel) {
		com.example.demo.entity.Product productEntity = new com.example.demo.entity.Product();
		productEntity.setProductId(productRepository.findAll().size() + 1);
		productEntity.setProductName(productModel.getProductName());
		return productEntity;
	}

	private com.example.demo.entity.Product updateEntityFromModel(com.example.demo.entity.Product productEntity, Product productModel) {
		productEntity.setProductId(productModel.getProductId());
		productEntity.setProductName(productModel.getProductName());
		return productEntity;
	}
}
