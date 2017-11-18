package com.epam.mentoring.web.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.epam.mentoring.client.IProductConsumer;
import com.epam.mentoring.data.model.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StockController {
	
	Logger logger = LoggerFactory.getLogger(StockController.class);

	private IProductConsumer productConsumer;
	
	@Autowired
	public StockController(IProductConsumer productConsumer){
		this.productConsumer = productConsumer;
	}
	
	@GetMapping("/")
	public String defaultPageRedirect() {
		return "redirect:stocklist";
	}
	
	@GetMapping("/stocklist")
	public String getStockList(Model model) {
		Map<Product, Integer> productsWithQuantities = productConsumer.getAllProductsWithQuantites();
//		logger.debug("getting products with quantities: " + productsWithQuantities.size() + " items found");
		model.addAttribute("productsWithQuantities", productsWithQuantities);
		return "stocklist";
	}

	@GetMapping("/add-product-income")
	public String saveProduct(@RequestParam("productName") String productName,
							  @RequestParam("price") float price,
							  @RequestParam("productTypeId") Integer productTypeId){


		return "redirect:stocklist";
	}
	
}
