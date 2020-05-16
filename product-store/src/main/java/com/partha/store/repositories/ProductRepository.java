package com.partha.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.partha.store.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
		
}
