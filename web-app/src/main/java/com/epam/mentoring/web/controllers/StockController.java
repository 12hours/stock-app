package com.epam.mentoring.web.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.epam.mentoring.client.IProductConsumer;
import com.epam.mentoring.data.model.Product;

@Controller
public class StockController {

//	private IProductConsumer productConsumer;
	
//	public StockController(IProductConsumer productConsumer){
//		this.productConsumer = productConsumer;
//	}
	
	@GetMapping("/")
	public String defaultPageRedirect() {
		return "redirect:stocklist";
	}
	
	@GetMapping("/test")
	public String test(Model model) {
		model.addAttribute("name", "victor");
		return "test";
	}
//	
//	@GetMapping("/stocklist")
//	public String getStockList(Model model) {
//		Map<Product, Integer> productsWithQuantites = productConsumer.getAllProductsWithQuantites();
//		model.addAttribute("productsWithQuantities", productsWithQuantites);
//		return "stocklist";
//	}
	
}
