package com.epam.mentoring.web.controllers;

import java.util.List;
import java.util.Map;

import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.epam.mentoring.client.ProductConsumer;
import com.epam.mentoring.data.model.Product;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class StockController {
	
	Logger logger = LoggerFactory.getLogger(StockController.class);

	private ProductConsumer productConsumer;
	
	@Autowired
	public StockController(ProductConsumer productConsumer){
		this.productConsumer = productConsumer;
	}
	
	@GetMapping("/")
	public String defaultPageRedirect() {
		return "redirect:stocklist";
	}
	
	@GetMapping("/stocklist")
	public String getStockList(Model model) {
//		Map<Product, Integer> productsWithQuantities = productConsumer.getAllProductsWithQuantites();
		List<ProductWithQuantityView> productsWithQuantitiesViews = productConsumer.getAllProductsWithQuantitiesViews();
//		logger.debug("getting products with quantities: " + productsWithQuantities.size() + " items found");
		model.addAttribute("productsWithQuantitiesViews", productsWithQuantitiesViews);
		return "stocklist";
	}

	@GetMapping("/operations")
	public String saveProduct(Model model){
		// TODO: implement
		return "redirect:stocklist";
	}
	
}
