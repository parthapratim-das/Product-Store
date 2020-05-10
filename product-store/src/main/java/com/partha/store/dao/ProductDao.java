package com.partha.store.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.partha.store.models.Product;
import com.partha.store.repositories.ProductRepository;


@Repository
public class ProductDao {
	
	@Autowired
	ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product save(Product product) {
		return productRepository.saveAndFlush(product);
	}

	public Optional<Product> getProductById(int productId) {
		return productRepository.findById(productId);
	}
	
	public void editProduct(Product product) {
		productRepository.save(product);
		
	}

	public void deleteProduct(int productId) {
		productRepository.deleteById(productId);
	}
	
	
	

}
