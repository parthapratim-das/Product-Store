package com.partha.store.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.partha.store.SwaggerConfig;
import com.partha.store.models.Product;
import com.partha.store.services.MvcProductService;

import io.swagger.annotations.Api;

@Api(tags = {SwaggerConfig.TAG_2})
@Controller
@RequestMapping("/mvc")
public class MvcController {
	
	@Autowired
	MvcProductService mvcProductService;
	
	Logger logger = LoggerFactory.getLogger(MvcController.class);
	
	@RequestMapping("/home")
	public ModelAndView loadHomePage()
	{
		logger.info("Inside MVC Controller loadHomePage() method");
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
	public ModelAndView loadAdminProducts(Authentication authentication)
	{
		logger.info("Inside MVC Controller loadAdminProducts() method");
		ModelAndView mv = new ModelAndView("products");
		mv.addObject("role", authentication.getAuthorities());
		List<Product> listProducts = new ArrayList<Product>();
		try {
			listProducts = mvcProductService.getAllProducts();
			mv.addObject("listProducts", listProducts);
			logger.info(listProducts.toString());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping("/admin/add")
	public ModelAndView addProducts(Authentication authentication)
	{
		ModelAndView mv = new ModelAndView("addproduct");
		mv.addObject("role", authentication.getAuthorities());
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
	public ModelAndView editProducts(@PathVariable int id, Product product, Authentication authentication)
	{
		ModelAndView mv = new ModelAndView("editproduct");
		mv.addObject("role", authentication.getAuthorities());
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
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "login";
    }

}
