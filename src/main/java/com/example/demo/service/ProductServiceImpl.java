package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.datamodel.Product;
import com.example.demo.entity.ProductEntity;
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
		Optional<ProductEntity> productOptional = productRepository.findById(productId);
		if (productOptional.isEmpty())
			throw new ProductNotFoundException();
		return createModelFromEntity(productOptional.get());
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
		Optional<ProductEntity> productFromDB = productRepository.findById(productId);
		if (productFromDB.isEmpty())
			throw new ProductNotFoundException();
		
		if (StringUtils.isEmpty(product.getProductId()))
			product.setProductId(productFromDB.get().getProductId());
		productRepository.save(updateEntityFromModel(productFromDB.get(), product));
	}

	@Override
	public void deleteProduct(Integer productId) {
		/*if (null == productsRepo.get(productId))
			throw new ProductNotFoundException();

		productsRepo.remove(productId);*/
		// Below is the method for version 2.X.X.
		// com.example.demo.entity.Product productFromDb = productRepository.findById(productId).orElse(null);
		// Below is the method for version 1.X.X.
		Optional<ProductEntity> productFromDB = productRepository.findById(productId);
		if (productFromDB.isEmpty())
			throw new ProductNotFoundException();
		productRepository.delete(productFromDB.get());
	}
	
	private Product createModelFromEntity(ProductEntity productFromDb) {
		Product product = new Product();
		product.setProductId(productFromDb.getProductId());
		product.setProductName(productFromDb.getProductName());
		return product;
	}

	private ProductEntity createEntityFromModel(Product productModel) {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setProductId(productRepository.findAll().size() + 1);
		productEntity.setProductName(productModel.getProductName());
		return productEntity;
	}

	private ProductEntity updateEntityFromModel(ProductEntity productEntity, Product productModel) {
		productEntity.setProductId(productModel.getProductId());
		productEntity.setProductName(productModel.getProductName());
		return productEntity;
	}
}
