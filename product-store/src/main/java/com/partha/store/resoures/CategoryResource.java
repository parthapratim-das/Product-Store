package com.partha.store.resoures;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.partha.store.dao.CategoryDao;
import com.partha.store.models.ProductCategory;

@RestController
@RequestMapping("/rest/category")
public class CategoryResource {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@GetMapping("/getAll")
	public List<ProductCategory> getAllCategories()
	{
		return categoryDao.getAllCategories();
	}
	
	@GetMapping("/get/{id}")
	public ProductCategory getProductById(@PathVariable("id") int catId)
	{
		return categoryDao.findCategoryById(catId);
	}
	
	@PostMapping("/add")
	public void addCategory(@RequestBody ProductCategory category)
	{
		categoryDao.addCategory(category);
	}
	
	@PutMapping("/update/{id}")
	public void updateCategoryById(@PathVariable("id") int catId, 
									@RequestBody ProductCategory category)
	{
		categoryDao.updateCategory(category);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteCategory(@PathVariable("id") int catId)
	{
		categoryDao.deleteCategory(catId);
	}

}
