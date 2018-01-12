package com.epam.mentoring.web.controller;

import com.epam.mentoring.data.model.dto.CollectionDTO;
import com.epam.mentoring.data.model.dto.view.ProductWithQuantityView;
import com.epam.mentoring.web.client.ProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    ProductServiceClient productServiceClient;

    @GetMapping({"","/","index"})
    public String index(Model model, HttpServletRequest request) {
        CollectionDTO<ProductWithQuantityView> productsWithQuantities = productServiceClient.findAllProductsWithQuantities();
        model.addAttribute("productsWithQuantities", productsWithQuantities.getItems());
        return "index";
    }

}
