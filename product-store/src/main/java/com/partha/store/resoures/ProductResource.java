package com.partha.store.resoures;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.partha.store.SwaggerConfig;
import com.partha.store.dao.ProductDao;
import com.partha.store.models.Product;


import io.swagger.annotations.Api;

@Api(tags = {SwaggerConfig.TAG_1})
@RestController
@RequestMapping("/rest")
public class ProductResource {
	
	@Autowired
	private ProductDao productDao;
	
	@GetMapping("/products")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAllProducts()
	{
		return productDao.getAllProducts();
	}
	
	@PostMapping("/addproduct")
	@Consumes(MediaType.APPLICATION_JSON)
	public Product addProduct(@RequestBody Product product)
	{
		return productDao.save(product);
	}
	
	@PutMapping("/editproduct/{id}")
	public Product editProduct(@PathVariable("id") int id, @RequestBody Product product)
	{
		Product updateProduct = new Product();
		Optional<Product> prodEntity = productDao.getProductById(product.getId());
		updateProduct = prodEntity.get();
		if(updateProduct != null && updateProduct.getId() != 0)
		{
			productDao.editProduct(product);
			return product;
		}
		return null;
	}
	
	@DeleteMapping("/deleteproduct/{id}")
	public Product deleteProduct(@PathVariable("id") int productId)
	{
		Product product = new Product();
		Optional<Product> prodEntity = productDao.getProductById(productId);
		product = prodEntity.get();
		if(product != null && product.getId() != 0)
		{
			productDao.deleteProduct(productId);
			return product;
		}
		return null;
	}

}
