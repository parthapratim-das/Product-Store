package com.partha.store.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.partha.store.models.Product;
import com.partha.store.services.MvcProductService;

@Controller
@RequestMapping("/mvc")
public class MvcController {
	
	@Autowired
	MvcProductService mvcProductService;
	
	@RequestMapping("/home")
	public ModelAndView loadHomePage()
	{
		ModelAndView mv = new ModelAndView("home");
		return mv;
	}
	
	@RequestMapping("/products")
	public ModelAndView loadProducts()
	{
		ModelAndView mv = new ModelAndView("products");
		mv.addObject("role", "ROLE_USER");
		List<Product> listProducts = new ArrayList<Product>();
		try {
			listProducts = mvcProductService.getAllProducts();
			mv.addObject("listProducts", listProducts);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return mv;
	}
	
	//Admin product management methods starts here
	
	@RequestMapping("/admin/products")
	public ModelAndView loadAdminProducts()
	{
		ModelAndView mv = new ModelAndView("products");
		mv.addObject("role", "ROLE_ADMIN");
		List<Product> listProducts = new ArrayList<Product>();
		try {
			listProducts = mvcProductService.getAllProducts();
			mv.addObject("listProducts", listProducts);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping("/admin/add")
	public ModelAndView addProducts()
	{
		ModelAndView mv = new ModelAndView("addproduct");
		mv.addObject("role", "ROLE_ADMIN");
		mv.addObject("command", new Product());
		return mv;
	}
	
	
	@RequestMapping(value="/admin/save",method = RequestMethod.POST)    
    public String save(@ModelAttribute("product") Product product){  
		System.out.println(product);
		try {
			mvcProductService.addProduct(product);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
        return "redirect:/mvc/admin/products";//will redirect to viewemp request mapping    
    }  
	
	@RequestMapping("/admin/edit/{id}")
	public ModelAndView editProducts(@PathVariable int id, Product product)
	{
		ModelAndView mv = new ModelAndView("editproduct");
		mv.addObject("role", "ROLE_ADMIN");
		List<Product> listProducts = new ArrayList<Product>();
		try {
			listProducts = mvcProductService.getAllProducts();
			Product editProduct = new Product();
			for(Product p : listProducts)
			{
				if(p.getId() == id)
					editProduct = p;
			}
			mv.addObject("command", editProduct);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping("/admin/editsave")
	public String editSave(@ModelAttribute("product") Product product)
	{
		mvcProductService.updateProduct(product);
		return "redirect:/mvc/admin/products";
	}
	
	@RequestMapping("/admin/delete/{id}")
	public String deleteProduct(@PathVariable int id, Product product)
	{
		mvcProductService.deleteProduct(id);
		return "redirect:/mvc/admin/products";
	}

}
