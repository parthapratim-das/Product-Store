package com.partha.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.partha.store.models.ProductCategory;

public interface CategoryRepository extends JpaRepository<ProductCategory, Integer>{

}
