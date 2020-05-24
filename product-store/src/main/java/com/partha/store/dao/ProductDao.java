package com.partha.store.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.partha.store.models.Product;
import com.partha.store.models.ProductCategory;
import com.partha.store.repositories.ProductRepository;


@Repository
public class ProductDao {
	
	@Autowired
	ProductRepository productRepository;

	//@Cacheable(value="productsCache")
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product save(Product product) {
		return productRepository.saveAndFlush(product);
	}

	@Cacheable(value="productsCache")
	public Optional<Product> getProductById(int productId) {
		return productRepository.findById(productId);
	}
	
	@CachePut(value="productsCache")
	public void editProduct(Product product) {
		productRepository.save(product);
		
	}
	
	@CacheEvict(value = "productCache")
	public void deleteProduct(int productId) {
		productRepository.deleteById(productId);
	}

	public List<Product> getProductByCategory(String category) {
		//ProductCategory p = new ProductCategory();
		//p.setId(Integer.parseInt(category));
		List<Product> productsByCategory = new ArrayList<>();
		productsByCategory = productRepository.findProductByCategoryId(Integer.parseInt(category));
		return productsByCategory;
	}
	
	
	

}
