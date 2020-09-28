package com.eval.coronakit.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eval.coronakit.entity.ProductMaster;
import com.eval.coronakit.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/home")
	public String home() {
		return "admin-home";
	}
	
	@GetMapping("/product-entry")
	public String productEntry(Model model) {
		model.addAttribute("product", new ProductMaster());
		return "add-new-item";
	}
	
	@PostMapping("/product-save")
	public String productSave(@ModelAttribute("product") @Valid ProductMaster product, BindingResult result, Model model ) {
			String view=null;
		
		if(result.hasErrors()) {
			view="add-new-item";
			
		}else {
			productService.addNewProduct(product);
			model.addAttribute("msg","Product Added Successfully.");
			model.addAttribute("products",productService.getAllProducts());
			view="show-all-item-admin";
		}
		return view;
	}
	

	@GetMapping("/product-list")
	public String productList(Model model) {
		System.out.println(productService.getAllProducts());
		model.addAttribute("products",productService.getAllProducts());
		return "show-all-item-admin";
	}
	
	@GetMapping("/product-delete/{productId}")
	public String productDelete(@PathVariable("productId") int productId, Model model) {
		productService.deleteProduct(productId);
		model.addAttribute("msg","Product Deleted Successfully.");
		model.addAttribute("products",productService.getAllProducts());
		return "show-all-item-admin";
	}
	
}
