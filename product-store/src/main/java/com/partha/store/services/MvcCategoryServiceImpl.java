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
import com.partha.store.models.ProductCategory;

@Service
public class MvcCategoryServiceImpl {
	
	@Autowired
	RestTemplate restTemplate;

	//method to invoke GET method in RestController
	public List<ProductCategory> getAllCategories() throws IOException
	{
		List<ProductCategory> categoryList = null;
		String URI = "http://localhost:8080/rest/category/getAll";
		String response = restTemplate.getForObject(URI, String.class);
		ObjectMapper mapper = new ObjectMapper();
		categoryList = mapper.readValue(response, new TypeReference<List<ProductCategory>>(){});
		return categoryList;
	}
	
	//method to invoke POST method in RestController
	public void addCategory(ProductCategory category) throws IOException
	{
		HttpEntity<ProductCategory> request = new HttpEntity<ProductCategory>(category);
		restTemplate.postForObject("http://localhost:8080/rest/category/add", request, ProductCategory.class);
	    
	}
	
	//method to invoke PUT method in RestController
	public void updateCategory(ProductCategory category)
	{
		String URI = "http://localhost:8080/rest/category/update/{id}";
		Map<String, String> params = new HashMap<String, String>();
	    params.put("id", Integer.toString(category.getId()));
		restTemplate.put ( URI, category, params);
	}
	
	//method to invoke DELETE method in RestController
	public void deleteProduct(int productId)
	{
		String URI = "http://localhost:8080/rest/category/delete/"+productId;
		restTemplate.delete(URI);
	}

}
