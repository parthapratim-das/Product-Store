package com.partha.store.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.partha.store.models.ProductCategory;
import com.partha.store.repositories.CategoryRepository;

@Repository
public class CategoryDao {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<ProductCategory> getAllCategories()
	{
		return categoryRepository.findAll();
	}
	
	public ProductCategory findCategoryById(int id)
	{
		Optional<ProductCategory> productCat = categoryRepository.findById(id);
		ProductCategory category = new ProductCategory();
		category = productCat.get();
		return category;
	}
	
	public void addCategory(ProductCategory category)
	{
		categoryRepository.saveAndFlush(category);
	}
	
	public void updateCategory(ProductCategory category)
	{
		categoryRepository.save(category);
	}
	
	public void deleteCategory(int catId)
	{
		categoryRepository.deleteById(catId);
	}

}
