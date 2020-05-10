package com.partha.store.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Products")
public class Product {
	
	@Id
	//@SequenceGenerator(name= "PRODUCT_SEQUENCE", sequenceName = "PRODUCT_SEQUENCE_ID", initialValue=107, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String productcode;
	private String productname;
	private double price;
	private String category;
	

}
