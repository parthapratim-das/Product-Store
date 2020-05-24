package com.partha.store.models;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
@Entity
@Table(name = "Products")
public class Product{
	
	
	@Id
	//@SequenceGenerator(name= "PRODUCT_SEQUENCE", sequenceName = "PRODUCT_SEQUENCE_ID", initialValue=107, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.AUTO)
	//@Column(name = "product_id")
	private int id;
	private String productcode;
	private String productname;
	private double price;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryid")
	//@JsonBackReference
	private ProductCategory category;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public ProductCategory getCategory() {
		return category;
	}
	public void setCategory(ProductCategory category) {
		this.category = category;
		//category.getProducts().add(this);
	}
	

}
