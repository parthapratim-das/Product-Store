package com.partha.store.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.partha.store.SwaggerConfig;
import com.partha.store.models.Product;
import com.partha.store.models.ProductCategory;
import com.partha.store.services.MvcCategoryServiceImpl;
import com.partha.store.services.MvcProductService;

import io.swagger.annotations.Api;

@Api(tags = {SwaggerConfig.TAG_2})
@Controller
@RequestMapping("/mvc")
public class MvcController {
	
	@Autowired
	MvcProductService mvcProductService;
	
	@Autowired
	private MvcCategoryServiceImpl categoryService;
	
	Logger logger = LoggerFactory.getLogger(MvcController.class);
	
	@RequestMapping("/home")
	public ModelAndView loadHomePage()
	{
		logger.info("Inside MVC Controller loadHomePage() method");
		ModelAndView mv = new ModelAndView("home");
		return mv;
	}
	
	@RequestMapping("/products")
	public ModelAndView loadProducts() throws IOException
	{
		
		ModelAndView mv = new ModelAndView("products");
		mv.addObject("role", "ROLE_USER");
		List<Product> listProducts = new ArrayList<Product>();
		List<ProductCategory> listCategory = categoryService.getAllCategories();
		try {
			listProducts = mvcProductService.getAllProducts();
			mv.addObject("listProducts", listProducts);
			mv.addObject("listCategory", listCategory);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping("/productByCategory")
	public String loadProducts(@PathParam("category") String category, 
	        Model model) throws IOException
	{
		System.out.println("Category**"+category);
		if(category == null)
		{
			category = "";
		}
		if (category.equalsIgnoreCase("all") || category.equalsIgnoreCase("")) {
	        return "redirect:/mvc/products";
	    } else {
	    	List<Product> listProducts = new ArrayList<Product>();
			List<ProductCategory> listCategory = categoryService.getAllCategories();
			try {
				listProducts = mvcProductService.getProductsListByCategory(category);
				model.addAttribute("listProducts", listProducts);
				model.addAttribute("listCategory", listCategory);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
	        return "products";          
	    }
	}
	
	
	
	//Admin product management methods starts here
	
	@RequestMapping("/admin/products")
	public ModelAndView loadAdminProducts(Authentication authentication)
	{
		logger.info("Inside MVC Controller loadAdminProducts() method");
		ModelAndView mv = new ModelAndView("products");
		mv.addObject("role", authentication.getAuthorities());
		mv.addObject("user",authentication.getName());
		List<Product> listProducts = new ArrayList<Product>();
		List<ProductCategory> listCategory = new ArrayList<>();
		try {
			listProducts = mvcProductService.getAllProducts();
			listCategory = categoryService.getAllCategories();
			mv.addObject("listProducts", listProducts);
			mv.addObject("listCategory", listCategory);
			logger.info(listProducts.toString());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping("/admin/productByCategory")
	public String loadAdminProductsByCategory(@PathParam("category") String category, 
	        Model model, Authentication authentication) throws IOException
	{
		System.out.println("Category**"+category);
		model.addAttribute("role", authentication.getAuthorities());
		model.addAttribute("user",authentication.getName());
		if(category == null)
		{
			category = "";
		}
		if (category.equalsIgnoreCase("all") || category.equalsIgnoreCase("")) {
	        return "redirect:/mvc/admin/products";
	    } else {
	    	List<Product> listProducts = new ArrayList<Product>();
			List<ProductCategory> listCategory = categoryService.getAllCategories();
			try {
				listProducts = mvcProductService.getProductsListByCategory(category);
				model.addAttribute("listProducts", listProducts);
				model.addAttribute("listCategory", listCategory);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
	        return "products";          
	    }
	}
	
	
	@RequestMapping("/admin/add")
	public ModelAndView addProducts(Authentication authentication) throws IOException
	{
		ModelAndView mv = new ModelAndView("addproduct");
		List<ProductCategory> listCategory = categoryService.getAllCategories();
		System.out.println(listCategory);
		mv.addObject("role", authentication.getAuthorities());
		mv.addObject("user",authentication.getName());
		mv.addObject("command", new Product());
		mv.addObject("listCategory", listCategory);
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
		mv.addObject("user",authentication.getName());
		List<Product> listProducts = new ArrayList<Product>();
		try {
			List<ProductCategory> listCategory = categoryService.getAllCategories();
			System.out.println(listCategory);
			listProducts = mvcProductService.getAllProducts();
			Product editProduct = new Product();
			for(Product p : listProducts)
			{
				if(p.getId() == id)
					editProduct = p;
			}
			mv.addObject("command", editProduct);
			mv.addObject("categoryname", editProduct.getCategory().getCategoryname());
			mv.addObject("listCategory", listCategory);
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
