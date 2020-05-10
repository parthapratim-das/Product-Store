package com.partha.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.partha.store.models.Product;

@Component
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
