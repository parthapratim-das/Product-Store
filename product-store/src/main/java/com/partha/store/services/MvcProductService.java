package com.partha.store.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.partha.store.models.Product;
import com.partha.store.resoures.ProductResource;

@Service
public class MvcProductService {
	
	@Autowired
	ProductResource productResource;
	
	@Autowired
	RestTemplate restTemplate;
	
	//method to invoke GET method in RestController
	public List<Product> getAllProducts() throws IOException
	{
		List<Product> productList = null;
		String URI = "http://localhost:8080/rest/products";
		//RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(URI, String.class);
		ObjectMapper mapper = new ObjectMapper();
        productList = mapper.readValue(response, new TypeReference<List<Product>>(){});
		return productList;
		////////Old approach//////////////////
		//List<Product> listOfProducts = productResource.getAllProducts();
		//return listOfProducts;
	}
	
	
	//method to invoke POST method in RestController
	public void addProduct(Product product) throws IOException
	{
		//RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Product> request = new HttpEntity<Product>(product);
		restTemplate.postForObject("http://localhost:8080/rest/addproduct", request, Product.class);
	    
	}
	
	//method to invoke PUT method in RestController
	public void updateProduct(Product product)
	{
		//RestTemplate restTemplate = new RestTemplate();
		String URI = "http://localhost:8080/rest/editproduct/{id}";
		Map<String, String> params = new HashMap<String, String>();
	    params.put("id", Integer.toString(product.getId()));
		restTemplate.put ( URI, product, params);
	}
	
	//method to invoke DELETE method in RestController
	public void deleteProduct(int productId)
	{
		//RestTemplate restTemplate = new RestTemplate();
		String URI = "http://localhost:8080/rest/deleteproduct/"+productId;
		restTemplate.delete(URI);
	}

}
